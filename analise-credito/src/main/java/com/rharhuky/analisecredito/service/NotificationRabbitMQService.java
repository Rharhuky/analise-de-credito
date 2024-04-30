package com.rharhuky.analisecredito.service;

import com.rharhuky.analisecredito.domain.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class NotificationRabbitMQService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(String exchangeName, Proposta proposta){
        rabbitTemplate.convertAndSend(exchangeName,"", proposta);
    }

}
