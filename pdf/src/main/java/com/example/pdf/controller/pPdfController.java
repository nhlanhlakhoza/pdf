package com.example.pdf.controller;

import com.example.pdf.model.PDFDocument;
import com.example.pdf.repo.PDFDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@CrossOrigin(origins = "http://localhost:4200") // Remove trailing slash
@RequestMapping("/user")
public class pPdfController {

    @Autowired
    private PDFDocumentRepository pdfDocumentRepository;


}
