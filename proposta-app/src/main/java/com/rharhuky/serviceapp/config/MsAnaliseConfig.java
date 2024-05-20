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

    @Value(value = "${rabbitmq.queue.proposta-concluida.dlq}")
    private String propostaConcluidaDlq;

    @Value(value = "${rabbitmq.exchange.proposta-concluida-dlx.ex}")
    private String dlxPropostaConcluida;

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
        return QueueBuilder.durable(queuePropostaConcluidaMsNotificacao)
//                .deadLetterExchange(dlxPropostaConcluida)
                .build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMSProposta(){
        return QueueBuilder
                .durable(queuePropostaConcluidaMsProposta)
                .deadLetterExchange(dlxPropostaConcluida)
                .build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaDlq(){
        return QueueBuilder
                .durable(propostaConcluidaDlq)
                .build();
    }

    @Bean
    public Binding createBindingPropostaConcluidaDlq(){
        return BindingBuilder
                .bind(createQueuePropostaConcluidaDlq())
                .to(deadLetterQueuePropostaConcluidaExchange());
    }

    @Bean
    public FanoutExchange deadLetterQueuePropostaConcluidaExchange(){
        return ExchangeBuilder
                .fanoutExchange(dlxPropostaConcluida)
                .build();
    }

}
