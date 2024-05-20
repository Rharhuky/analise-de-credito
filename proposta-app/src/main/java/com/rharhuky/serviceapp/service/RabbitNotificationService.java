package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.entity.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RabbitNotificationService {

    private RabbitTemplate rabbitTemplate;

    public void notifyRabbitMqQueue(Proposta proposta, String exchange){
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

    public void notifyRabbitMqQueue(Proposta proposta, String exchange, MessagePostProcessor messagePostProcessor){
        this.rabbitTemplate.convertAndSend(exchange, "", proposta, messagePostProcessor);
    }

}
