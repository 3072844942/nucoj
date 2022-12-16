package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.enums.FilePathEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 更新服务
 */
public interface UploadService {
    /**
     * 上传图片
     * @param file 图片
     * @return 地址
     */
    String upload(MultipartFile file, FilePathEnum filePath);

    /**
     * 上传题目测试数据
     * @param file 压缩包
     * @return
     * @throws IOException
     */
    String uploadRecord(MultipartFile file) throws IOException;
}
