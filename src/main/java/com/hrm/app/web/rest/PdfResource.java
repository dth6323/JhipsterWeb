package com.hrm.app.web.rest;

import com.hrm.app.domain.PdfDocument;
import com.hrm.app.repository.PdfDocumentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class PdfResource {

    @Autowired
    private PdfDocumentRepository repository;

    @GetMapping
    public List<PdfDocument> search(@RequestParam("keyword") String keyword) {
        return repository.findByContentContaining(keyword);
    }
}
