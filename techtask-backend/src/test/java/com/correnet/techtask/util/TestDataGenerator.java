package com.correnet.techtask.util;

import com.correnet.techtask.domain.Image;
import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.dto.ImageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class TestDataGenerator {
    public static MockMultipartFile generateMultipartFile(){
        return new MockMultipartFile(
                "test.file.name",
                "test.file.name.txt",
                "test/plain",
                "Hello, World!".getBytes()
        );
    }

    public static List<Image> generateListOfImages(){
        return generateListOfUrls()
                .stream()
                .map(url -> {
                    Image image = new Image();
                    image.setImageUrl(url);
                    return image;
                }).collect(Collectors.toList());
    }

    public static List<Tag> generateListOfTags(){
        List<Image> images = generateListOfImages();
        Tag tag1 = new Tag();
        tag1.setName("ball");
        tag1.setImage(images.get(0));
        Tag tag2 = new Tag();
        tag2.setName("ball");
        tag2.setImage(images.get(1));
        Tag tag3 = new Tag();
        tag3.setName("sun");
        tag3.setImage(images.get(2));

        return List.of(tag1,tag2,tag3);
    }

    public static Image generateImage() {
        Image image = new Image();
        image.setId(1L);
        image.setImageUrl("someImage");
        image.setTags(generateListOfTags());
        return image;
    }

    public static Tag generateTag() {
        Tag tag = new Tag();
        tag.setName("ball");
        tag.setId(4L);

        Image image = new Image();
        image.setId(1L);
        image.setImageUrl("someImage");

        tag.setImage(image);
        return tag;

    }

    public static ResponseEntity<String> generate2xxResponseEntity() {
        return new ResponseEntity<>("""
                {
                    "status": {
                        "code": 10000,
                        "description": "Ok",
                        "req_id": "9140eff7a0097f3bb4132eb6adb6d4aa"
                    },
                    "outputs": [
                        {
                            "id": "363ef354ad974306b36d6f6397dcd82d",
                            "status": {
                                "code": 10000,
                                "description": "Ok"
                            },
                            "created_at": "2024-03-16T16:08:57.497273410Z",
                            "model": {
                                "id": "general-image-recognition",
                                "name": "Image Recognition",
                                "created_at": "2016-03-09T17:11:39.608845Z",
                                "modified_at": "2023-12-18T08:54:35.172716Z",
                                "app_id": "main",
                                "model_version": {
                                    "id": "aa7f35c01e0642fda5cf400f543e7c40",
                                    "created_at": "2018-03-06T21:10:24.454834Z",
                                    "status": {
                                        "code": 21100,
                                        "description": "Model is trained and ready"
                                    },
                                    "visibility": {
                                        "gettable": 50
                                    },
                                    "app_id": "main",
                                    "user_id": "clarifai",
                                    "metadata": {}
                                },
                                "user_id": "clarifai",
                                "model_type_id": "visual-classifier",
                                "visibility": {
                                    "gettable": 50
                                },
                                "toolkits": [],
                                "use_cases": [],
                                "languages": [],
                                "languages_full": [],
                                "check_consents": [],
                                "workflow_recommended": false,
                                "image": {
                                    "url": "https://data.clarifai.com/large/users/clarifai/apps/main/input_owners/luv_2261/inputs/image/00ba04ce6efa93784f4473a96d5ee9b0",
                                    "hosted": {
                                        "prefix": "https://data.clarifai.com",
                                        "suffix": "users/clarifai/apps/main/input_owners/luv_2261/inputs/image/00ba04ce6efa93784f4473a96d5ee9b0",
                                        "sizes": [
                                            "large",
                                            "small"
                                        ],
                                        "crossorigin": "use-credentials"
                                    }
                                }
                            },
                            "input": {
                                "id": "7c67016bc5a64907a06fa194e27f561e",
                                "data": {
                                    "image": {
                                        "url": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/2019-05-18_Fu%C3%9Fball%2C_Frauen%2C_UEFA_Women%27s_Champions_League%2C_Olympique_Lyonnais_-_FC_Barcelona_StP_1192_LR10_by_Stepro%28Cropped%29.jpg/1200px-2019-05-18_Fu%C3%9Fball%2C_Frauen%2C_UEFA_Women%27s_Champions_League%2C_Olympique_Lyonnais_-_FC_Barcelona_StP_1192_LR10_by_Stepro%28Cropped%29.jpg"
                                    }
                                }
                            },
                            "data": {
                                "concepts": [
                                    {
                                        "id": "ai_fKppxwrj",
                                        "name": "soccer",
                                        "value": 0.99645704,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_mXT38hpL",
                                        "name": "football",
                                        "value": 0.9825418,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_v55szW7r",
                                        "name": "ball",
                                        "value": 0.9821554,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_7WNVdPhm",
                                        "name": "competition",
                                        "value": 0.9720409,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_z0CRw3d3",
                                        "name": "soccer player",
                                        "value": 0.96606255,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_JbZtzpnP",
                                        "name": "stadium",
                                        "value": 0.96471924,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_lklvFKL4",
                                        "name": "soccer ball",
                                        "value": 0.93413645,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_dxSG2s86",
                                        "name": "man",
                                        "value": 0.9287987,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_3rjpTxQx",
                                        "name": "match",
                                        "value": 0.92651045,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_tCxWWQ3t",
                                        "name": "foot",
                                        "value": 0.90458155,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_fhqpCCSD",
                                        "name": "athlete",
                                        "value": 0.8991682,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_vwd31qqd",
                                        "name": "sports equipment",
                                        "value": 0.89649993,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_ntt6Zlfl",
                                        "name": "outfit",
                                        "value": 0.88232213,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_JJ5jqrxM",
                                        "name": "soccer field",
                                        "value": 0.87316954,
                                        "app_id": "main"
                                    },
                                    {
                                        "id": "ai_mPGx9LMN",
                                        "name": "action energy",
                                        "value": 0.8581964,
                                        "app_id": "main"
                                    }               
                                ]
                            }
                        }
                    ]
                }
                """, HttpStatus.OK);
    }
    public static ResponseEntity<String> generateUnvalidResponseEntity() {
        return new ResponseEntity<>("json", HttpStatus.NOT_FOUND);
    }

    public static ImageDto generateImageDto() {
        return new ImageDto(1L,"someUrl");
    }


    public static List<String> generateListOfUrls() {
        String url1 = "first url";
        String url2 = "second url";;
        String url3 = "third url";
        String url4 = "fourth url";

        return List.of(url1,url2,url3,url4);
    }
}
