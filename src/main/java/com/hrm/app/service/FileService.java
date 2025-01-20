package com.hrm.app.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.hrm.app.domain.MinioConfig;
import com.hrm.app.domain.PdfDocument;
import com.hrm.app.repository.PdfDocumentRepository;
import com.hrm.app.service.dto.SearchResultDTO;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final MinioConfig minioConfig;
    private final MinioClient minioClient;
    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    private PdfDocumentRepository fileDocumentRepository;

    public FileService(MinioConfig minioConfig, ElasticsearchClient elasticsearchClient) {
        this.minioConfig = minioConfig;
        this.minioClient = MinioClient.builder()
            .endpoint(minioConfig.getUrl())
            .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
            .build();
        this.elasticsearchClient = elasticsearchClient;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String bucketName = minioConfig.getBucketName();

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

    public List<SearchResultDTO> searchDocuments(String searchTerm) throws IOException {
        try {
            // Tạo highlight configuration
            Highlight highlight = new Highlight.Builder()
                .fields("content", h -> h.preTags("<mark>").postTags("</mark>").numberOfFragments(3).fragmentSize(150))
                .build();

            // Build search request
            SearchRequest request = new SearchRequest.Builder()
                .index("search-waws")
                .query(q -> q.match(m -> m.field("content").query(searchTerm)))
                .highlight(highlight)
                .source(config -> config.filter(f -> f.includes("content", "fileName")))
                .build();

            // Thực hiện search
            SearchResponse<SearchResultDTO> response = elasticsearchClient.search(request, SearchResultDTO.class);

            // Convert kết quả sang DTO
            List<SearchResultDTO> results = new ArrayList<>();

            for (Hit<SearchResultDTO> hit : response.hits().hits()) {
                SearchResultDTO dto = new SearchResultDTO();
                dto.setFileName(hit.id());
                Map<String, List<String>> highlightFields = hit.highlight();
                if (highlightFields != null && highlightFields.containsKey("content")) {
                    // Nếu có highlight, lấy nội dung đã highlight
                    dto.setHighlight(highlightFields.get("content").get(0));
                }
                results.add(dto);
            }

            return results;
        } catch (Exception e) {
            throw e;
        }
    }

    private String extractContentFromPdf(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            return new PDFTextStripper().getText(document); // Trích xuất nội dung
        }
    }

    //    public List<SearchResultDTO> searchFilesWithHighlight(String keyword) {
    //        List<Map<String, Object>> rawResults = fileDocumentRepository.findByContentWithHighlight(keyword);
    //        List<SearchResultDTO> results = new ArrayList<>();
    //
    //        for (Map<String, Object> result : rawResults) {
    //            String fileName = (String) ((Map<String, Object>) result.get("_source")).get("fileName");
    //            List<String> highlights = (List<String>) ((Map<String, Object>) result.get("highlight")).get("content");
    //
    //            SearchResultDTO searchResult = new SearchResultDTO();
    //            searchResult.setFileName(fileName);
    //            searchResult.setHighlightedContent(String.join(" ... ", highlights));
    //            results.add(searchResult);
    //        }
    //
    //        return results;
    //    }

    public List<Map<String, Object>> test(String keyword) {
        List<Map<String, Object>> rawResults = fileDocumentRepository.findByContentWithHighlight(keyword);
        return rawResults;
    }

    public List<String> searchFilesByContent(String keyword) {
        List<String> matchingFiles = new ArrayList<>();
        try {
            // Tìm kiếm trong Elasticsearch
            Iterable<PdfDocument> searchResults = fileDocumentRepository.findByContentContaining(keyword);

            // Lấy danh sách tên file từ kết quả tìm kiếm
            for (PdfDocument document : searchResults) {
                matchingFiles.add(document.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while searching for files", e);
        }
        return matchingFiles;
    }

    public byte[] getFile(String fileName) {
        String bucketName = minioConfig.getBucketName(); // Lấy tên bucket từ cấu hình
        try {
            GetObjectResponse objectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());

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
