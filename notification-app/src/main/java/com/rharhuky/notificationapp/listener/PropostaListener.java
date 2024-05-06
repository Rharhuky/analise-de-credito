package com.rharhuky.notificationapp.listener;

import com.rharhuky.notificationapp.domain.Proposta;
import com.rharhuky.notificationapp.notificationUtils.MensagemConstants;
import com.rharhuky.notificationapp.service.SnsService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropostaListener {

    private final SnsService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente-ms-notificacao}")
    public void propostaPendente(Proposta proposta){
        var message = String.format(MensagemConstants.PROPOSTA_ANALISE, proposta.getUser().getNome());
        notificar(message, proposta.getUser().getTelefone());
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta-concluida-ms-notificacao}")
    public void propostaConcluida(Proposta proposta){
        String messageResponse;
        if(proposta.getAprovado()){
            messageResponse = String.format(MensagemConstants.PROPOSTA_APROVADA, proposta.getUser().getNome());
        }
        else
            messageResponse = String.format(MensagemConstants.PROPOSTA_NEGADA, proposta.getUser().getNome(), proposta.getObservacao());
        notificar(messageResponse, proposta.getUser().getTelefone());
    }

    private void notificar(String message, String phone){
        snsService.notificar(snsService.createPublishRequest(message, phone));
    }

}
