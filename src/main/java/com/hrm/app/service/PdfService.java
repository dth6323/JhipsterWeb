package com.hrm.app.service;

import com.hrm.app.domain.PdfDocument;
import com.hrm.app.repository.PdfDocumentRepository;
import java.io.File;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.util.UUID;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    @Autowired
    private PdfDocumentRepository repository;

    public void savePDF(String filePath) throws IOException {
        File file = new File(filePath);

        PdfDocument document = new PdfDocument();
        document.setId(UUID.randomUUID().toString()); // Tạo ID duy nhất
        document.setFileName(file.getName()); // Lưu tên file
        document.setContent(extractText(filePath)); // Lưu nội dung trích xuất từ file

        repository.save(document);
    }

    public static String extractText(String filePath) throws IOException {
        Tika tika = new Tika();
        try {
            return tika.parseToString(new File(filePath));
        } catch (TikaException e) {
            throw new RuntimeException(e);
        }
    }
}
