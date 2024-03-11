package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    @Bean
    public Queue createQueuePropostaPendenteMSAnaliseCredito(){
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

    @Bean
    public FanoutExchange createFannoutExchangePropostaPendente(){
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Binding createBindingPropostaPendenteQueueAndAnaliseCreditoMS(){
        return BindingBuilder.bind(createQueuePropostaPendenteMSAnaliseCredito()).
                to(createFannoutExchangePropostaPendente());
    }
    @Bean
    public Binding createBindingPropostaPendenteQueueAndNotificacaoMS(){
        return BindingBuilder.bind(createQueuePropostaPendenteMSNotificacao()).
                to(createFannoutExchangePropostaPendente());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
