package com.correnet.techtask.controller;

import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.service.ImageService;
import com.correnet.techtask.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ImageController.class)
@AutoConfigureMockMvc()
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class ImageControllerTest {


    @MockBean
    ImageService imageService;
    @Autowired
    MockMvc mockMvc;



    @Test
    public void Should_return_image_link_when_file_valid() throws Exception {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

        when(imageService.uploadImageFile(any(MultipartFile.class))).thenReturn("someUrl");

        mockMvc.perform(multipart("/api/image/file")
                        .file("file", multipartFile.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                        .andExpect(status().isOk())
                        .andExpect(content().string("someUrl"));
    }

    @Test
    public void Should_return_image_link_when_url_valid() throws Exception {
        when(imageService.uploadImageUrl("someUrl")).thenReturn("someUrl");

        mockMvc.perform(post("/api/image/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("image","someUrl"))
                        .andExpect(status().isOk())
                        .andExpect(content().string("someUrl"));
    }

    @Test
    public void Should_return_list_of_all_images() throws Exception {
        List<String> imageUrls = TestDataGenerator.generateListOfUrls();

        when(imageService.getImages(null)).thenReturn(imageUrls);
        String expectedJsonResponse = "[\"first url\",\"second url\",\"third url\",\"fourth url\"]";

        mockMvc.perform(get("/api/image")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse));
    }

}
