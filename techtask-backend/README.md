# Backend
![Java](https://img.shields.io/badge/Java-17-orange) ![spring](https://img.shields.io/badge/SpringBoot-V_3.23-green)  ![postgres](https://img.shields.io/badge/PostgreSQL-V_16-blue) ![rabbitMq](https://img.shields.io/badge/RabbitMQ-V_3.11-orange) ![clarifai](https://img.shields.io/badge/CLARIFAI%20API-blue) ![clarifai](https://img.shields.io/badge/Maven-blue)

## About
Project use a ***1*** controller for images.
Main endpoints ***are*** : 
```java
@PostMapping("/file")
    public String saveImageFile(@RequestParam(value = "file") MultipartFile multipartFile){
        return imageService.uploadImageFile(multipartFile);
    }
```
```java
@PostMapping("/url")
    public String saveImageUrl(@RequestParam(value = "image") String imageUrl){
        return imageService.uploadImageUrl(imageUrl);
    }
```
```java
@GetMapping
    public List<String> getImages(@RequestParam(value = "tag",required = false) String tagName){
        return imageService.getImages(tagName);
    }
```
