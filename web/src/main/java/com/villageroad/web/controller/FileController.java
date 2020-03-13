package com.villageroad.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by flybird on 2018/4/25.
 */
@Slf4j
@RestController
public class FileController extends BaseController {
    private static final String MEDIA_DIR = "/home/user/media_upload";

    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    public ResponseEntity fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return createResponseEntity("", "fail");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        log.info(fileName + "-->" + size);

        String filePath = MEDIA_DIR + "/" + UUID.randomUUID();
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return createResponseEntity(filePath);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return createResponseEntity("", "fail");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return createResponseEntity("", "fail");
        }
    }

    /**
     * 实现多文件上传
     **/
    @RequestMapping(value = "/multi/fileUpload", method = RequestMethod.POST)
    public ResponseEntity multifileUpload(@RequestParam("fileName") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return createResponseEntity(Collections.emptyList(), "fail");
        }

        List<String> filePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            log.info(fileName + "-->" + size);

            if (file.isEmpty()) {
                return createResponseEntity(Collections.emptyList(), "fail");
            } else {
                String filePath = MEDIA_DIR + "/" + UUID.randomUUID();
                filePaths.add(filePath);
                File dest = new File(filePath);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return createResponseEntity(Collections.emptyList(), "fail");
                }
            }
        }
        return createResponseEntity(filePaths);
    }
}

