package com.rharhuky.serviceapp.scheduler;

import com.rharhuky.serviceapp.entity.Proposta;
import com.rharhuky.serviceapp.repository.PropostaRepository;
import com.rharhuky.serviceapp.service.RabbitNotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class PropostaSemIntegracao {

    private PropostaRepository propostaRepository;

    private RabbitNotificationService rabbitNotificationService;

    private String propostaPendenteExchange;


    public PropostaSemIntegracao(PropostaRepository propostaRepository,
                                 RabbitNotificationService rabbitNotificationService,
                                 @Value("${rabbitmq.propostapendente.exchange}")
                                 String propostaPendenteExchange) {
        this.propostaRepository = propostaRepository;
        this.rabbitNotificationService = rabbitNotificationService;
        this.propostaPendenteExchange = propostaPendenteExchange;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasNaoIntegradas(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try{
                rabbitNotificationService.notifyRabbitMqQueue(proposta, propostaPendenteExchange);
                atualizarProposta(proposta);
                log.info("Send with Success");
            }
            catch (RuntimeException exception){
                log.error(exception.getMessage());
            }
        });
    }

    @Transactional
    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
