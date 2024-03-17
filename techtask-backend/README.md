# Backend
![Java](https://img.shields.io/badge/Java-17-orange) ![spring](https://img.shields.io/badge/SpringBoot-V_3.23-green)  ![postgres](https://img.shields.io/badge/PostgreSQL-V_16-blue) ![rabbitMq](https://img.shields.io/badge/RabbitMQ-V_3.11-orange) ![clarifai](https://img.shields.io/badge/CLARIFAI%20API-blue) ![clarifai](https://img.shields.io/badge/Maven-blue)

## About
The project uses a ***single*** controller for managing images.

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
After validating image file *(if you sending file)* ***ImageService*** generate a URL for link, sends it to the ***DB***, and Uploads the image file to an ***AWS S3 bucket***

When image sended ***RabbitMQService*** sends a message for queue to ***RabbitMQAdapter*** listener and ***ClarifaiAdapter*** makes a request to external ***Clarifai API*** to get tags and items that image contains and saves it in ***DB***.  

### Testing
Project is unit tested for all main components: *controller*,*service*,*adapter*,*convertor*.
