package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsAnaliseConfig {

    @Value("${rabbitmq.queue.proposta-concluida.exchange}")
    private String exchangePropostaConcluida;

    @Value("${rabbitmq.queue.proposta-concluida-ms-proposta}")
    private String queuePropostaConcluidaMsProposta;

    @Value("${rabbitmq.queue.proposta-concluida-ms-notificacao}")
    private String queuePropostaConcluidaMsNotificacao;

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
        return QueueBuilder.durable(queuePropostaConcluidaMsNotificacao).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMSProposta(){
        return QueueBuilder.durable(queuePropostaConcluidaMsProposta).build();
    }

}
