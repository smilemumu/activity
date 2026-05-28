package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    private final FileUploadService fileUploadService;

    public CommonController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        String url = fileUploadService.upload(file);
        return Result.success("上传成功", url);
    }
}
