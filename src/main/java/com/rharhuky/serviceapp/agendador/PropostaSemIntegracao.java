package com.rharhuky.serviceapp.agendador;

import com.rharhuky.serviceapp.entity.Proposta;
import com.rharhuky.serviceapp.repository.ProposalRepository;
import com.rharhuky.serviceapp.service.RabbitNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private ProposalRepository proposalRepository;

    private RabbitNotificationService rabbitNotificationService;

    private String exchange;

    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(ProposalRepository proposalRepository,
                                 RabbitNotificationService rabbitNotificationService,
                                 @Value("${rabbitmq.propostapendente.exchange}")
                                 String exchange) {
        this.proposalRepository = proposalRepository;
        this.rabbitNotificationService = rabbitNotificationService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasNaoIntegradas(){
        proposalRepository.findAllByIntegradoIsFalse().forEach(proposta -> {
            try{
                rabbitNotificationService.notifyRabbitMqQueue(proposta, exchange);
                atualizarProposta(proposta);
                logger.info("Send with Sucess");
            }
            catch (RuntimeException exception){
                logger.error(exception.getMessage());
            }
        });
    }

    @Transactional
    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrado(true);
        proposalRepository.save(proposta);
    }
}
