package com.julian21olarte.thymeleafexample.services;

import org.springframework.beans.factory.annotation.Autowired;
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
        return this.storeFile(file, this.getUploadsPath(pathName));
    }

    @Override
    public boolean storeFile(MultipartFile file, Path path) throws IOException {
        if(!file.isEmpty()) {
            Files.copy(file.getInputStream(), path); // store file
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFile(Path path) throws IOException {
        return Files.deleteIfExists(path);
    }

    @Override
    public boolean deleteFileFromUploadsFolder(String pathName) throws IOException {
        return this.deleteFile(this.getUploadsPath(pathName));
    }

    @Override
    public Path getUploadsPath(String fileName) {
        return Paths.get("uploads").resolve(fileName).toAbsolutePath();
    }
}
