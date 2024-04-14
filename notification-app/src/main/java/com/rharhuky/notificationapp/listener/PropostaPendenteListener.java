package com.rharhuky.notificationapp.listener;

import com.rharhuky.notificationapp.domain.Proposta;
import com.rharhuky.notificationapp.notificationUtils.MensagemConstants;
import com.rharhuky.notificationapp.service.SnsService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropostaPendenteListener {


    private final SnsService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        var message = String.format(MensagemConstants.PROPOSTA_ANALISE, proposta.getUser().getNome());
        snsService.notificar(message, proposta.getUser().getTelefone());
    }

}
