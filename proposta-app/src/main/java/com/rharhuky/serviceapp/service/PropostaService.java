package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaRequestDto;
import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import com.rharhuky.serviceapp.entity.Proposta;
import com.rharhuky.serviceapp.mapper.PropostaMapper;
import com.rharhuky.serviceapp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    private RabbitNotificationService rabbitNotificationService;

    private String exchange;


    public PropostaService(PropostaRepository propostaRepository,
                           RabbitNotificationService rabbitNotificationService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.rabbitNotificationService = rabbitNotificationService;
        this.exchange = exchange;
    }

    public PropostaResponseDto create(PropostaRequestDto propostaRequestDto){
        var theProposal = PropostaMapper.CONVERT.convertToProposta(propostaRequestDto);
        propostaRepository.save(theProposal);
        notifyQueues(theProposal);
        return PropostaMapper.CONVERT.convertToPropostaResponseDto(theProposal);
    }

    private void notifyQueues(Proposta proposta){
        try{
            rabbitNotificationService.notifyRabbitMqQueue(proposta, exchange);
        }
        catch (RuntimeException runtimeException){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> findAll(){
        return PropostaMapper.CONVERT.convertListToListResponse(this.propostaRepository.findAll());
    }

}
