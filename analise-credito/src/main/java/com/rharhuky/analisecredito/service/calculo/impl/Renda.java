package com.rharhuky.analisecredito.service.calculo.impl;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.calculo.CalculoPonto;

public class Renda implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (proposta.getUser().getRenda() < proposta.getValorSolicitado())
            throw new RuntimeException("Valor fora dos limites");
        return percentualDeRendaSobreValorSolicitado(proposta.getUser().getRenda(), proposta.getValorSolicitado());

    }

    private int percentualDeRendaSobreValorSolicitado(Double renda, Double valorSolicitado){
        if(renda <= 0.04 * valorSolicitado)
            return 30;
        else if(renda <= 0.25 * valorSolicitado)
            return 45;
        else
            return 50;
    }
}
