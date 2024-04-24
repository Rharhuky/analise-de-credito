package com.rharhuky.analisecredito.listener;

import com.rharhuky.analisecredito.domain.Proposta;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropostaPendenteListener {

    @RabbitListener(queues = {"${rabbitmq.queue.proposta-pendente}"})
    public void propostaEmAnalise(Proposta proposta){

    }

}
