package com.rharhuky.serviceapp.listener;

import com.rharhuky.serviceapp.entity.Proposta;
import com.rharhuky.serviceapp.mapper.PropostaMapper;
import com.rharhuky.serviceapp.repository.PropostaRepository;
import com.rharhuky.serviceapp.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta-concluida-ms-proposta}")
    public void concluirProposta(Proposta proposta){
        propostaRepository.save(proposta);
        webSocketService.notificar(PropostaMapper.CONVERT.convertToPropostaResponseDto(proposta));
    }

}
