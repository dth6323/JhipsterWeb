package com.hrm.app.service;

import com.hrm.app.domain.MinioConfig;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final MinioConfig minioConfig;
    private final MinioClient minioClient;

    public FileService(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
        this.minioClient = MinioClient.builder()
            .endpoint(minioConfig.getUrl())
            .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
            .build();
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String bucketName = minioConfig.getBucketName(); // Lấy tên bucket từ cấu hình

        // Kiểm tra bucket có tồn tại hay không
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExists) {
            // Tạo bucket nếu chưa tồn tại
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // Tải file lên MinIO
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(file.getOriginalFilename()) // Tên file
                .stream(file.getInputStream(), file.getSize(), -1) // Dữ liệu
                .contentType(file.getContentType()) // Loại file
                .build()
        );

        // Trả về URL của file
        return minioConfig.getUrl() + "/" + bucketName + "/" + file.getOriginalFilename();
    }

    // Đọc file từ MinIO
    public String readFileContentFromMinIO(String fileName) throws Exception {
        InputStream inputStream = minioClient.getObject(
            GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName).build()
        );

        if (fileName.endsWith(".docx") || fileName.endsWith(".doc")) {
            return extractContentFromDocFile(inputStream, fileName);
        }
        return null;
    }

    private String extractContentFromDocFile(InputStream inputStream, String fileName) throws IOException {
        if (fileName.endsWith(".docx")) {
            XWPFDocument docx = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            return extractor.getText();
        }
        return null;
    }

    // Tìm kiếm file theo nội dung
    public List<String> searchFilesByContent(String keyword) throws Exception {
        List<String> matchingFiles = new ArrayList<>();
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(minioConfig.getBucketName()).build());

        for (Result<Item> result : results) {
            String fileName = result.get().objectName();
            try {
                String content = readFileContentFromMinIO(fileName);
                if (content != null && content.contains(keyword)) {
                    matchingFiles.add(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Bỏ qua lỗi nếu không đọc được file
            }
        }
        return matchingFiles;
    }

    public byte[] getFile(String fileName) {
        String bucketName = minioConfig.getBucketName(); // Lấy tên bucket từ cấu hình
        try {
            // Lấy file từ MinIO
            GetObjectResponse objectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());

            // Đọc nội dung file thành mảng byte
            return objectResponse.readAllBytes();
        } catch (MinioException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file from MinIO", e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listFiles() {
        String bucketName = minioConfig.getBucketName(); // Lấy tên bucket từ cấu hình
        List<String> fileNames = new ArrayList<>();
        try {
            // Liệt kê tất cả các đối tượng trong bucket
            Iterable<Result<Item>> objects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());

            // Duyệt qua các đối tượng và lấy tên của chúng
            for (Result<Item> result : objects) {
                Item item = result.get();
                fileNames.add(item.objectName()); // Thêm tên file vào danh sách
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileNames;
    }
}
