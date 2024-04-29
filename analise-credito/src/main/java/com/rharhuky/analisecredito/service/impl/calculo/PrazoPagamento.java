package com.rharhuky.analisecredito.service.impl.calculo;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.stereotype.Service;

@Service
public class PrazoPagamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() <= 120 ? 60 : 0;
    }
}
