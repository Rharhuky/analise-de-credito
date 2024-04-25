package com.rharhuky.analisecredito.service.calculo.impl;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.calculo.CalculoPonto;

import java.util.Random;

/**
 * Bacen
 */
public class OutrosCreditosEmAndamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return hasOutrosCreditosEmAndamento() ? 0 : 10;
    }

    private boolean hasOutrosCreditosEmAndamento(){
        return new Random().nextBoolean();
    }

}
