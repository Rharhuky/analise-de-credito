package com.rharhuky.analisecredito.listener;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.interfaces.analise.AnaliseService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class PropostaPendenteListener {

    private final AnaliseService analiseService;

    @RabbitListener(queues = {"${rabbitmq.queue.proposta-pendente}"})
    public void propostaEmAnalise(Proposta proposta){
        analiseService.calcular(proposta);
    }

}
