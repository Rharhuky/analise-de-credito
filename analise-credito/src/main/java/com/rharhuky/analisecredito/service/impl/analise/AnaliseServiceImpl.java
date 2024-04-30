package com.rharhuky.analisecredito.service.impl.analise;

import com.rharhuky.analisecredito.annotations.AnaliseException;
import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.NotificationRabbitMQService;
import com.rharhuky.analisecredito.service.interfaces.analise.AnaliseService;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnaliseServiceImpl implements AnaliseService {

    @Value(value = "${rabbitmq.exchange.proposta.concluida}")
    private String exchangePropostaConcluida;

    @Autowired
    private List<CalculoPonto> calculoPontoList;

    @Autowired
    private NotificationRabbitMQService notificationRabbitMQService;

    @Value(value = "${app.pontuacao.minima}")
    private int pontuacaoMinima;

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
            AnaliseException analiseException = exception.getClass().getAnnotation(AnaliseException.class);
            if(Objects.nonNull(analiseException)){
                proposta.setObservacao(System.getenv(analiseException.value()));
            }
            proposta.setAprovado(false);
        }
        notificationRabbitMQService.notificar(exchangePropostaConcluida, proposta);
    }

}
