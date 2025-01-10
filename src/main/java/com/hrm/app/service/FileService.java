package com.hrm.app.service;

import com.hrm.app.domain.MinioConfig;
import com.hrm.app.domain.PdfDocument;
import com.hrm.app.repository.PdfDocumentRepository;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final MinioConfig minioConfig;
    private final MinioClient minioClient;

    @Autowired
    private PdfDocumentRepository fileDocumentRepository;

    public FileService(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
        this.minioClient = MinioClient.builder()
            .endpoint(minioConfig.getUrl())
            .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
            .build();
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String bucketName = minioConfig.getBucketName();

        // Kiểm tra và tạo bucket nếu cần
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // Tải file lên MinIO
        String fileName = file.getOriginalFilename();
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build()
        );

        // Trích xuất nội dung từ file PDF và lưu vào Elasticsearch
        String content = extractContentFromFile(file.getInputStream(), fileName);
        if (content != null) {
            PdfDocument fileDocument = new PdfDocument();
            fileDocument.setId(fileName); // Sử dụng tên file làm ID
            fileDocument.setFileName(fileName);
            fileDocument.setContent(content);
            fileDocumentRepository.save(fileDocument); // Lưu vào Elasticsearch
        }

        return minioConfig.getUrl() + "/" + bucketName + "/" + fileName;
    }

    // Đọc file từ MinIO
    public String readFileContentFromMinIO(String fileName) throws Exception {
        InputStream inputStream = minioClient.getObject(
            GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName).build()
        );

        if (fileName.endsWith(".docx") || fileName.endsWith(".doc")) {
            return extractContentFromFile(inputStream, fileName);
        }
        return null;
    }

    private String extractContentFromFile(InputStream inputStream, String fileName) throws IOException {
        if (fileName.endsWith(".pdf")) {
            return extractContentFromPdf(inputStream);
        }
        return null;
    }

    private String extractContentFromPdf(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            return new PDFTextStripper().getText(document); // Trích xuất nội dung
        }
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
