package com.rharhuky.analisecredito.service.impl.calculo;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.exceptions.CalculoException;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.stereotype.Service;

@Service
public class Renda implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (proposta.getUser().getRenda() <  0.1 * proposta.getValorSolicitado())
            throw new CalculoException();
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
