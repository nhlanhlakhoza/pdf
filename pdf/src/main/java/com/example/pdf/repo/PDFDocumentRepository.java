package com.example.pdf.repo;

import com.example.pdf.model.PDFDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PDFDocumentRepository extends JpaRepository<PDFDocument, Long> {

}
