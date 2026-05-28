package com.example.activityConfig.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${file.upload.path:./uploads/}")
    private String uploadPath;

    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件为空");
        }

        String originalName = file.getOriginalFilename();
        String ext = "";
        if (originalName != null && originalName.contains(".")) {
            ext = originalName.substring(originalName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + ext;

        File dir = new File(uploadPath);
        if (!dir.isAbsolute()) {
            dir = new File(System.getProperty("user.dir"), uploadPath);
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File dest = new File(dir, fileName);
            file.transferTo(dest);
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }
    }
}
