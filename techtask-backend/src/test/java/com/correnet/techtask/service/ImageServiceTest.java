package com.correnet.techtask.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.correnet.techtask.domain.Image;
import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.dto.ImageDto;
import com.correnet.techtask.repository.ImageRepository;
import com.correnet.techtask.util.FileConvertor;
import com.correnet.techtask.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ImageServiceTest {
    @InjectMocks
    private ImageService imageService;

    @Mock
    private AmazonS3 amazonS3;

    @Mock
    private FileConvertor fileConvertor;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private TagService tagService;

    @Mock
    private RabbitMQService rabbitMQService;

    @Test
    public void Should_return_all_images_when_no_tag(){
        String tag = null;
        List<Image> listOfImages = TestDataGenerator.generateListOfImages();;

        when(imageRepository.findAll()).thenReturn(listOfImages);
        List<String> imageUrls = imageService.getImages(tag);

        assertEquals(4,listOfImages.size());
    }

    @Test
    public void Should_return_tagged_images_when_tag_passed(){
        String tag = "ball";
        List<Tag> listOfTags = TestDataGenerator.generateListOfTags();

        when(tagService.getTagsByName(tag)).thenReturn(listOfTags);
        List<String> imageUrls = imageService.getImages(tag);

        assertEquals(3,imageUrls.size());
    }

    @Test
    public void Should_return_image_url_when_imageUrl_passed(){
        String imageUrl = "someImage";
        Image image = TestDataGenerator.generateImage();

        when(imageRepository.save(any(Image.class))).thenReturn(image);

        String urlImage = imageService.uploadImageUrl(imageUrl);

        assertEquals("someImage",urlImage);
    }

    @Test
    public void Should_return_image_url_when_file_is_image() throws IOException {
        MultipartFile multipartFile = TestDataGenerator.generateMultipartFile();
        InputStream inputStream = Mockito.mock(InputStream.class);
        ReflectionTestUtils.setField(imageService,"bucketLink","someBucket");

        try(MockedStatic<URLConnection> mockedStatic = mockStatic(URLConnection.class)){
            when(URLConnection.guessContentTypeFromStream(inputStream)).thenReturn("image");
            when(fileConvertor.convertMultiFileToInputStream(multipartFile)).thenReturn(inputStream);

            String url = imageService.uploadImageFile(multipartFile);

            assertTrue(url.startsWith("someBucket"));
        }


    }

    @Test
    public void Should_throw_runtime_when_file_is_not_image() throws IOException {
        MultipartFile multipartFile = TestDataGenerator.generateMultipartFile();
        InputStream inputStream = Mockito.mock(InputStream.class);
        ReflectionTestUtils.setField(imageService,"bucketLink","someBucket");

        try(MockedStatic<URLConnection> mockedStatic = mockStatic(URLConnection.class)){
            when(URLConnection.guessContentTypeFromStream(inputStream)).thenReturn(null);
            when(fileConvertor.convertMultiFileToInputStream(multipartFile)).thenReturn(inputStream);

            assertThrows(RuntimeException.class, () -> imageService.uploadImageFile(multipartFile));
        }
    }



}
