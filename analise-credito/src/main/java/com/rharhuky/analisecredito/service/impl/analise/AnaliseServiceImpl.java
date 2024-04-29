package com.rharhuky.analisecredito.service.impl.analise;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.interfaces.analise.AnaliseService;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnaliseServiceImpl implements AnaliseService {

    private List<CalculoPonto> calculoPontoList;

    @Override
    public int calcular(Proposta proposta) {
        return calculoPontoList
                .stream()
                .mapToInt(calculoPonto -> calculoPonto.calcular(proposta))
                .sum();
    }

}
