package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsPropostaNotificationConfig {

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String exchangePropostaConcluida;

    @Value("${rabbitmq.queue.proposta-concluida}")
    private String queuePropostaConcluidaMsProposta;

    @Bean
    public FanoutExchange createFannoutExchangePropostaConcluida(){
        return ExchangeBuilder.fanoutExchange(exchangePropostaConcluida).build();
    }

    @Bean
    public Binding createBindingPropostaConcluidaQueueAndPropostaMS(){
        return BindingBuilder.bind(createQueuePropostaConcluidaMSProposta()).
                to(createFannoutExchangePropostaConcluida());
    }

    @Bean
    public Binding createBindingPropostaConcluidaQueueAndNotificationMS(){
        return BindingBuilder.bind(createQueuePropostaConcluidaMSNotificacao()).
                to(createFannoutExchangePropostaConcluida());
    }

    @Bean
    public Queue createQueuePropostaConcluidaMSNotificacao(){
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }
    @Bean
    public Queue createQueuePropostaConcluidaMSProposta(){
        return QueueBuilder.durable(queuePropostaConcluidaMsProposta).build();
    }


}
