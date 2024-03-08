package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue criarQueuePropostaPendenteMSAnaliseCredito(){
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMSNotificacao(){
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }
    @Bean
    public Queue createQueuePropostaConcluidaMSProposta(){
        return QueueBuilder.durable("proposta-concluida.ms-proposta ").build();
    }
    @Bean
    public Queue createQueuePropostaConcluidaMSNotificacao(){
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    private final ConnectionFactory connectionFactory;

    @Autowired
    public RabbitMQConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
