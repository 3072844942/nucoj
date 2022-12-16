package com.q7g.nucoj_spring.controller;


import com.q7g.nucoj_spring.enums.FilePathEnum;
import com.q7g.nucoj_spring.service.UploadService;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传模块
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传方法
     * @param file
     * @return
     */
    @PostMapping("/img/upload")
    @ApiOperation(value = "图片上传方法")
    public Result<String> uploadImage(@RequestBody MultipartFile file) {
        return Result.ok(uploadService.upload(file, FilePathEnum.AVATAR));
    }

    /**
     * 题目测试数据上传方法
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/record/upload")
    @ApiOperation(value = "题目测试数据上传方法")
    public Result<String> uploadRecord(@RequestBody MultipartFile file) throws IOException {
        return Result.ok(uploadService.uploadRecord(file));
    }
}
