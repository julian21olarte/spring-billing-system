package com.julian21olarte.thymeleafexample.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IStoreService {

    boolean storeFileInUploadsFolder(MultipartFile file, String pathName) throws IOException;
    boolean storeFile(MultipartFile file, Path path) throws IOException;
    boolean deleteFile(Path path) throws IOException;
    boolean deleteFileFromUploadsFolder(String pathName) throws IOException;
    Path getUploadsPath(String fileName);
}
