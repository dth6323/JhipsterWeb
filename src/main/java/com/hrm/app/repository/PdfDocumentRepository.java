package com.hrm.app.repository;

import com.hrm.app.domain.PdfDocument;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PdfDocumentRepository extends ElasticsearchRepository<PdfDocument, String> {
    List<PdfDocument> findByContentContaining(String keyword);
}
