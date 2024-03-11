package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    private RabbitTemplate rabbitTemplate;

    public void notifyRabbitMqQueue(PropostaResponseDto propostaResponseDto, String exchange){
        this.rabbitTemplate.convertAndSend(exchange, "", propostaResponseDto);
    }

}
