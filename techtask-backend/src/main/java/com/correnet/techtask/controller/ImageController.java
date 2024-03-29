package com.correnet.techtask.controller;

import com.correnet.techtask.dto.ImageDto;
import com.correnet.techtask.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "YOUR_FRONTEND_URL")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/file")
    public String saveImageFile(@RequestParam(value = "file") MultipartFile multipartFile){
        return imageService.uploadImageFile(multipartFile);
    }

    @PostMapping("/url")
    public String saveImageUrl(@RequestParam(value = "image") String imageUrl){
        return imageService.uploadImageUrl(imageUrl);
    }

    @GetMapping
    public List<String> getImages(@RequestParam(value = "tag",required = false) String tagName){
        return imageService.getImages(tagName);
    }

}
