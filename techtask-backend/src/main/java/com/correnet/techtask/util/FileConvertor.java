package com.correnet.techtask.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FileConvertor {
    public InputStream convertMultiFileToInputStream(MultipartFile multipartFile){
        try {
            return new ByteArrayInputStream(multipartFile.getBytes());
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }
}
