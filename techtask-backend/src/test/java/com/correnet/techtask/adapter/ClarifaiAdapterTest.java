package com.correnet.techtask.adapter;

import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.dto.ImageDto;
import com.correnet.techtask.service.ImageService;
import com.correnet.techtask.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClarifaiAdapterTest {
    @InjectMocks
    private ClarifaiAdapter clarifaiAdapter;
    
    @Mock
    private ImageService imageService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void Should_return_valid_tags_from_json(){
        ResponseEntity<String> responseEntity = TestDataGenerator.generate2xxResponseEntity();
        ReflectionTestUtils.setField(clarifaiAdapter,"apiUrl","someUrl");
        ImageDto imageDto = TestDataGenerator.generateImageDto();
        ArgumentMatcher<List<Tag>> listSize3Matcher = list -> list.size() == 5;
        ArgumentMatcher<List<Tag>> tagNameMatcher = list -> list.get(0).getName().equals("soccer");;


        when(restTemplate.postForEntity(eq("someUrl"),any(HttpEntity.class),eq(String.class))).thenReturn(responseEntity);


        clarifaiAdapter.getTagsFromImage(imageDto);

        verify(imageService).setImageTags(eq(imageDto.getId()),argThat(listSize3Matcher));
        verify(imageService).setImageTags(eq(imageDto.getId()),argThat(tagNameMatcher));

    }

    @Test
    public void Should_return_when_response_not_200(){
        ResponseEntity<String> responseEntity = TestDataGenerator.generateUnvalidResponseEntity();
        ReflectionTestUtils.setField(clarifaiAdapter,"apiUrl","someUrl");
        ImageDto imageDto = TestDataGenerator.generateImageDto();


        when(restTemplate.postForEntity(eq("someUrl"),any(HttpEntity.class),eq(String.class))).thenReturn(responseEntity);


        clarifaiAdapter.getTagsFromImage(imageDto);

        verify(imageService,never()).setImageTags(any(),any());
    }

}
