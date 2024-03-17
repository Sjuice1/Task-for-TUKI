package com.correnet.techtask.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.correnet.techtask.domain.Image;
import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.dto.ImageDto;
import com.correnet.techtask.repository.ImageRepository;
import com.correnet.techtask.util.FileConvertor;
import jakarta.servlet.ServletContext;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 amazonS3;
    private final FileConvertor fileConvertor;
    private final TagService tagService;
    private final RabbitMQService rabbitMQService;
    private final ImageRepository imageRepository;


    @Value("${aws.bucketLink}")
    private String bucketLink;

    @Value("${aws.bucketName}")
    private String bucketName;



    public String uploadImageFile(MultipartFile multipartFile) {
        String uniqueID = UUID.randomUUID().toString();
        String fileExtension = multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename()
                .lastIndexOf("."));
        String fullName = uniqueID + fileExtension;
        String imageLink = bucketLink + fullName;

        try(InputStream inputStream = fileConvertor.convertMultiFileToInputStream(multipartFile)){
            if(URLConnection.guessContentTypeFromStream(inputStream) == null
                    || !URLConnection.guessContentTypeFromStream(inputStream).contains("image")){
                throw new RuntimeException();
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            Image image = new Image();
            image.setImageUrl(imageLink);

            amazonS3.putObject(new PutObjectRequest(bucketName,fullName,inputStream,objectMetadata));
            imageRepository.save(image);
            rabbitMQService.sendMessageToExtractTags(new ImageDto(image.getId(),imageLink));

            return imageLink;
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    public String uploadImageUrl(@NotNull String imageUrl) {
        Image image = new Image();
        image.setImageUrl(imageUrl);
        imageRepository.save(image);
        rabbitMQService.sendMessageToExtractTags(new ImageDto(image.getId(),imageUrl));
        return imageUrl;
    }

    public List<String> getImages(String tagName){
        if(tagName == null){
            return imageRepository.findAll()
                    .stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());
        }
        return tagService.getTagsByName(tagName)
                .stream()
                .map(Tag::getImage)
                .map(Image::getImageUrl)
                .collect(Collectors.toList());
    }

    public void setImageTags(Long id, List<Tag> tags) {
        Image image = imageRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        tags.forEach(tag -> {
            tag.setImage(image);
            tagService.saveTag(tag);;
        });
    }
}
