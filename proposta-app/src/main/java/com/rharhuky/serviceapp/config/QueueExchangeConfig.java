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

    @Bean
    public Queue createQueuePropostaPendenteMSAnaliseCredito(){
        return QueueBuilder.durable(queuePropostaPendenteMsAnaliseCredito).build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMSNotificacao(){
        return QueueBuilder.durable(queuePropostaPendenteMsNotificacao).build();
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
