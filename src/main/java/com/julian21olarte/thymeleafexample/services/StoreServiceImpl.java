package com.julian21olarte.thymeleafexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    ServletContext context;

    @Override
    public boolean storeFileInUploadsFolder(MultipartFile file, String pathName) throws IOException {
        Path rootPath = Paths.get("uploads").resolve(pathName).toAbsolutePath();
        return this.storeFile(file, rootPath);
    }

    @Override
    public boolean storeFile(MultipartFile file, Path path) throws IOException {
        if(!file.isEmpty()) {
            Files.copy(file.getInputStream(), path); // store file
            return true;
        }
        return false;
    }
}
