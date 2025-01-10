package com.hrm.app.web.rest;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.hrm.app.domain.PdfDocument;
import com.hrm.app.service.FileService;
import com.hrm.app.service.dto.SearchResultDTO;
import io.minio.ListObjectsArgs;
import io.minio.Result;
import io.minio.messages.Item;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // API tải file lên MinIO
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Tải file lên MinIO và lấy URL trả về
            String fileUrl = fileService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    // API tải file xuống từ MinIO
    @GetMapping("/filesall")
    public List<String> listFiles() {
        return fileService.listFiles();
    }

    @GetMapping("/readfiles")
    public ResponseEntity<?> getFile(@RequestParam("filename") String fileName) {
        try {
            byte[] fileBytes = fileService.getFile(fileName);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileBytes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam("keyword") String keyword) throws IOException {
        return ResponseEntity.ok(fileService.searchDocuments(keyword));
    }

    @GetMapping("/search-by-content")
    public ResponseEntity<List<String>> searchFilesByContent(@RequestParam("keyword") String keyword) {
        try {
            List<String> matchingFiles = fileService.searchFilesByContent(keyword);
            return ResponseEntity.ok(matchingFiles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
    }

    @GetMapping("/searchWithHighlight")
    public ResponseEntity<List<SearchResultDTO>> searchFileWithHighlight(@RequestParam("keyword") String keyword) {
        try {
            List<SearchResultDTO> results = fileService.searchDocuments(keyword);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
