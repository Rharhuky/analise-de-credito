package com.rharhuky.analisecredito.service.impl.analise;

import com.rharhuky.analisecredito.annotations.AnaliseException;
import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.NotificationRabbitMQService;
import com.rharhuky.analisecredito.service.interfaces.analise.AnaliseService;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class AnaliseServiceImpl implements AnaliseService {

    @Value(value = "${app.pontuacao.minima}")
    private int pontuacaoMinima;

    @Value(value = "${rabbitmq.exchange.proposta.concluida}")
    private String exchangePropostaConcluida;

    @Autowired
    private List<CalculoPonto> calculoPontoList;

    @Autowired
    private NotificationRabbitMQService notificationRabbitMQService;

    @Autowired
    private MessageSource messageSource;


    @Override
    public void calcular(Proposta proposta) {
        try{
            var pontuacao = calculoPontoList
                    .stream()
                    .mapToInt(calculoPonto -> calculoPonto.calcular(proposta))
                    .sum();
            proposta.setAprovado(pontuacao > pontuacaoMinima);
        }
        catch (Exception exception){
            AnaliseException analiseException = AnnotationUtils.findAnnotation(exception.getClass(), AnaliseException.class);
            if(Objects.nonNull(analiseException)){
                var message = messageSource.getMessage(analiseException.value(), null, new Locale("pt", "Brazil"));
                proposta.setObservacao(message);
            }
            proposta.setAprovado(false);
        }
        notificationRabbitMQService.notificar(exchangePropostaConcluida, proposta);
    }

}
