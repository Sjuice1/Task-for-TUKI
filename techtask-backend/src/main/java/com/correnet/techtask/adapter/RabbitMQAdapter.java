package com.correnet.techtask.adapter;

import com.correnet.techtask.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQAdapter {

    private final ClarifaiAdapter clarifaiAdapter;

    @RabbitListener(queues = "imageToTagsQueue")
    public void tagsQueueSubscriber(ImageDto imageDto){
        clarifaiAdapter.getTagsFromImage(imageDto);
    }
}
