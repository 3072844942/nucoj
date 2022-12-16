package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.enums.FilePathEnum;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.service.UploadService;
import com.q7g.nucoj_spring.util.UnPackeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.q7g.nucoj_spring.enums.FilePathEnum.AVATAR;
import static com.q7g.nucoj_spring.enums.FilePathEnum.JUDGE;

@Service
public class UploadServiceImpl implements UploadService {
    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;

    /**
     * 访问url
     */
    @Value("${upload.local.url}")
    private String localUrl;

    @Override
    public String upload(MultipartFile file, FilePathEnum filePath) {
        if (file.isEmpty()) throw new BizException("上传文件错误");
        String originFileName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + originFileName.substring(originFileName.lastIndexOf('.'));
        File dest = new File(localPath + filePath.getPath() + fileName);
        if (!dest.getAbsoluteFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BizException("上传失败");
        }
        return localUrl + filePath.getPath() + fileName;
    }

    @Override
    public String uploadRecord(MultipartFile file) throws IOException {
        String originFileName = file.getOriginalFilename();
        // 文件类型后缀
        String type = originFileName.substring(originFileName.lastIndexOf('.'));
        // 解压路径  本地路径/数据文件夹/系统当前时间/
        long time = System.currentTimeMillis();
        String path = localPath + JUDGE.getPath() + time + '/';
        File dest = new File(path + originFileName);
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        if (type.equalsIgnoreCase(".zip")) {
            UnPackeUtil.unPackZip(dest, null, path);
        }
        else if (type.equalsIgnoreCase(".rar")) {
            UnPackeUtil.unPackRar(dest, path);
        }
        else throw new BizException("文件类型错误");
        dest.delete();
        return String.valueOf(time);
    }
}
