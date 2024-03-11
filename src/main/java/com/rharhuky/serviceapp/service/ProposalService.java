package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaRequestDto;
import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import com.rharhuky.serviceapp.mapper.PropostaMapper;
import com.rharhuky.serviceapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    private ProposalRepository proposalRepository;


    private NotificationService notificationService;

    private String exchange;


    public ProposalService(ProposalRepository proposalRepository,
                           NotificationService notificationService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public PropostaResponseDto create(PropostaRequestDto propostaRequestDto){
        var theProposal = PropostaMapper.CONVERT.convertToProposta(propostaRequestDto);
        var response = PropostaMapper.CONVERT.convertToPropostaResponseDto(proposalRepository.save(theProposal));
        notificationService.notifyRabbitMqQueue(response, exchange);
        return response;
    }

    public List<PropostaResponseDto> findAll(){
        return PropostaMapper.CONVERT.convertListToListResponse(this.proposalRepository.findAll());
    }

}
