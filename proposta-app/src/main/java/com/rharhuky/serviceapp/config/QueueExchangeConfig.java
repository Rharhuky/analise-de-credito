package com.rharhuky.serviceapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueExchangeConfig {

    @Value("${rabbitmq.proposta-pendente.exchange}")
    private String exchangePropostaPendente;

    @Value("${rabbitmq.queue.proposta-pendente-ms-analise-credito}")
    private String queuePropostaPendenteMsAnaliseCredito;

    @Value("${rabbitmq.queue.proposta-pendente-ms-notificacao}")
    private String queuePropostaPendenteMsNotificacao;

    @Value("${rabbitmq.exchange.proposta-pendente-dlx.ex}")
    private String dlxPropostaPendente;

    @Value(value = "${rabbitmq.queue.proposta-pendente.dlq}")
    private String queuePropostaPendenteDlq;

    @Bean
    public Queue createQueuePropostaPendenteMSAnaliseCredito(){
        return QueueBuilder.durable(queuePropostaPendenteMsAnaliseCredito)
                .deadLetterExchange(dlxPropostaPendente)
                .maxLength(10L)
                .maxPriority(5)
                .ttl(10000)
                .build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMSNotificacao(){
        return QueueBuilder.durable(queuePropostaPendenteMsNotificacao).build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteDlq() {
        return QueueBuilder
                .durable(queuePropostaPendenteDlq)
                .build();
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return ExchangeBuilder.fanoutExchange(dlxPropostaPendente).build();
    }

    @Bean
    public Binding criarBinding(){
        return BindingBuilder.bind(criarFilaPropostaPendenteDlq()).to(deadLetterExchange());
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
