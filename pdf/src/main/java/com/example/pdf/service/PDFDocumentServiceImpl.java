package com.example.pdf.service;


import com.example.pdf.model.PDFDocument;
import com.example.pdf.repo.PDFDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PDFDocumentServiceImpl implements PDFDocumentService{
    @Autowired
    private PDFDocumentRepository pdfDocumentRepository;

    @Override
    public PDFDocument findById(Long id) {
        return pdfDocumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<PDFDocument> findAll() {
        return pdfDocumentRepository.findAll();
    }

    @Override
    public void save(PDFDocument pdfDocument) {
        pdfDocumentRepository.save(pdfDocument);
    }
}
