package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchangePropostaPendente;


    @Bean
    public Queue createQueuePropostaPendenteMSAnaliseCredito(){
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMSNotificacao(){
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    public FanoutExchange createFannoutExchangePropostaPendente(){
        return ExchangeBuilder.fanoutExchange(exchangePropostaPendente).build();
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


}
