package com.correnet.techtask.convertor;

import com.correnet.techtask.util.FileConvertor;
import com.correnet.techtask.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FileConvertorTest {
    private final FileConvertor fileService = new FileConvertor();

    @Test
    void Should_return_file_when_converting_multipartfile() throws IOException {
        MultipartFile multipartFile = TestDataGenerator.generateMultipartFile();

        InputStream inputStream = fileService.convertMultiFileToInputStream(multipartFile);

        byte[] bytes = new byte[13];
        inputStream.read(bytes);

        assertEquals("Hello, World!", new String(bytes));
    }
}
