package com.correnet.techtask.adapter;

import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.dto.ImageDto;
import com.correnet.techtask.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClarifaiAdapter {
    @Value("${clarifai.api_url}")
    private String apiUrl;

    @Value("${clarifai.authorization_key}")
    private String authKey;

    private final RestTemplate restTemplate;
    private final ImageService imageService;

    public void getTagsFromImage(ImageDto imageDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization","Key " + authKey);

        String jsonRequest = """
                {
                    "inputs": [
                      {
                        "data": {
                          "image": {
                            "url": "%s"
                          }
                        }
                      }
                    ]
                  }""".formatted(imageDto.getImageUrl());

        HttpEntity<String> httpEntity = new HttpEntity<>(jsonRequest,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl,httpEntity,String.class);

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            return;
        }

        List<Tag> tags = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());

        JSONArray outputsArray = jsonObject.getJSONArray("outputs");
        for (int i = 0; i < outputsArray.length(); i++) {

            JSONObject outputObj = outputsArray.getJSONObject(i);
            JSONObject dataObj = outputObj.getJSONObject("data");
            JSONArray conceptsArray = dataObj.getJSONArray("concepts");

            for (int j = 0; j < 5; j++) {
                JSONObject conceptObj = conceptsArray.getJSONObject(j);
                Tag tag = new Tag();
                tag.setName(conceptObj.getString("name"));
                tags.add(tag);
            }
        }

        imageService.setImageTags(imageDto.getId(),tags);
    }
}
