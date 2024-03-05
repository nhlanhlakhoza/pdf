package com.example.pdf.service;

import com.example.pdf.model.PDFDocument;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PDFDocumentService {
    PDFDocument findById(Long id);
    List<PDFDocument> findAll();
    void save(PDFDocument pdfDocument);
}
