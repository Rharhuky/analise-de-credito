package com.rharhuky.analisecredito.service.impl.calculo;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OutrosCreditosEmAndamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return hasOutrosCreditosEmAndamento() ? 0 : 10;
    }

    private boolean hasOutrosCreditosEmAndamento(){
        return new Random().nextBoolean();
    }

}
