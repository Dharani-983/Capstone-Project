package com.loan_transaction.dto;

import org.springframework.web.multipart.MultipartFile;

import com.loan_transaction.domain.DocumentType;

import lombok.Data;

@Data
public class DocumentDTO {
    private MultipartFile file;
    private DocumentType documentType;
}