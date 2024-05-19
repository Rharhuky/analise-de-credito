package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private  SimpMessagingTemplate simpMessagingTemplate;

    public void notificar(PropostaResponseDto propostaResponseDto){
        simpMessagingTemplate.convertAndSend("/propostas", propostaResponseDto);
    }

}
