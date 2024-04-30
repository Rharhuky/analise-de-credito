package com.rharhuky.analisecredito.service.impl.analise;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.exceptions.CalculoException;
import com.rharhuky.analisecredito.service.interfaces.analise.AnaliseService;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnaliseServiceImpl implements AnaliseService {

    private List<CalculoPonto> calculoPontoList;

    @Value(value = "${app.pontuacao.minima}")
    private int pontuacaoMinima;

    @Override
    public void calcular(Proposta proposta) {

        try{
            var aprovada = calculoPontoList
                    .stream()
                    .mapToInt(calculoPonto -> calculoPonto.calcular(proposta))
                    .sum() > pontuacaoMinima;
            proposta.setAprovado(aprovada);
        }
        catch (CalculoException calculoException){
            proposta.setAprovado(false);
        }
    }

}
