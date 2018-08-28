package com.julian21olarte.thymeleafexample.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StoreServiceImpl implements IStoreService {


    @Override
    public boolean storeFileInUploadsFolder(MultipartFile file, String pathName) throws IOException {
        Path rootPath = Paths.get("uploads").resolve(pathName).toAbsolutePath();
        return this.storeFile(file, rootPath);
    }

    @Override
    public boolean storeFile(MultipartFile file, Path path) throws IOException {
        if(!file.isEmpty()) {
            Files.copy(file.getInputStream(), path); // store photo
            return true;
        }
        return false;
    }
}
