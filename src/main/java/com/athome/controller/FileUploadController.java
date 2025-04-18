package com.athome.controller;

import com.athome.pojo.Result;
import com.athome.util.COSUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result fileUpload(MultipartFile file) throws IOException {
        String orinalFileName = file.getOriginalFilename();
        System.out.println(orinalFileName);
        String fileName = UUID.randomUUID()+orinalFileName.substring(orinalFileName.lastIndexOf('.'));
        System.out.println(fileName);
        String url = COSUtil.uploadFile("exam-project/"+fileName,file.getInputStream());
        return Result.success(url);
    }

}
