package com.rharhuky.notificationapp.listener;

import com.rharhuky.notificationapp.domain.Proposta;
import com.rharhuky.notificationapp.notificationUtils.MensagemConstants;
import com.rharhuky.notificationapp.service.SnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PropostaPendenteListener {

    private SnsService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        snsService.notificar(
                String.format(MensagemConstants.PROPOSTA_ANALISE, proposta.getUser().getNome())
        );
    }

}
