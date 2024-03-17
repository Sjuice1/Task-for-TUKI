package com.correnet.techtask.service;

import com.correnet.techtask.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToExtractTags(ImageDto imageDto){
        rabbitTemplate.convertAndSend("imageToTagsExchange","imageToTagsRK", imageDto);
    }

}
