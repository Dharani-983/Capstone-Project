package com.loan_transaction.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loan_transaction.constants.ErrorMessages;
import com.loan_transaction.exception.FileStorageException;

@Service
public class FileStorageService {

    private final Path storageLocation;

    public FileStorageService() {
        this.storageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(storageLocation);
        } catch (IOException e) {
            throw new FileStorageException(ErrorMessages.STORAGE_DIR_CREATION_FAILED, e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetLocation = storageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation);
            return filename;
        } catch (IOException e) {
            throw new FileStorageException(ErrorMessages.FILE_STORAGE_FAILED + file.getOriginalFilename(), e);
        }
    }
}