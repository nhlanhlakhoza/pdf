package com.example.pdf.controller;


import com.example.pdf.model.PDFDocument;
import com.example.pdf.service.PDFDocumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Remove trailing slash
@RequestMapping("/user")
public class PDFController {

    @Autowired
    private PDFDocumentService pdfDocumentService;

    @GetMapping("/ping")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }



    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        List<PDFDocument> pdfDocuments = pdfDocumentService.findAll();
        mv.addObject("pdfDocuments", pdfDocuments);
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView addPDF() {
        return new ModelAndView("addpdf");
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPDFPost(@RequestParam("pdfFile") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("PDF file is empty");
        }

        try {
            PDFDocument pdfDocument = new PDFDocument();
            Blob pdfBlob = new SerialBlob(file.getBytes());
            pdfDocument.setPdf(pdfBlob);
            pdfDocumentService.save(pdfDocument);
            return ResponseEntity.ok(pdfDocumentService);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF file");
        }
    }

    @GetMapping("/download/pdf/{id}")
    public ResponseEntity<byte[]> downloadPDF(@PathVariable Long id) {
        try {
            PDFDocument pdfDocument = pdfDocumentService.findById(id);
            if (pdfDocument == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Blob pdfContent = pdfDocument.getPdf();
            byte[] pdfBytes = pdfContent.getBytes(1, (int) pdfContent.length());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "document.pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }}
