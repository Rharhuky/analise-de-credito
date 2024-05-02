package com.rharhuky.serviceapp.listener;

import com.rharhuky.serviceapp.entity.Proposta;
import com.rharhuky.serviceapp.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    @RabbitListener(queues = "${rabbitmq.queue.proposta-concluida}")
    public void concluirProposta(Proposta proposta){
        propostaRepository.save(proposta);
    }

}
