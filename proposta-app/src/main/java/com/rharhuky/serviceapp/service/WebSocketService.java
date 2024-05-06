package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void notificar(PropostaResponseDto propostaResponseDto){
        simpMessagingTemplate.convertAndSend("/proposta", propostaResponseDto);
    }

}
