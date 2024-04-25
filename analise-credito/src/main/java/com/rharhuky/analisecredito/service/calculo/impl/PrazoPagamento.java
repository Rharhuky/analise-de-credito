package com.rharhuky.analisecredito.service.calculo.impl;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.calculo.CalculoPonto;

public class PrazoPagamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() <= 120 ? 60 : 0;
    }
}
