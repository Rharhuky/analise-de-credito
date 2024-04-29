package com.rharhuky.analisecredito.service.impl.calculo;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Order(value = 2)
public class PontuacaoScore implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        var score = getScore();

        if(score <= 200)
            throw new RuntimeException("Score baixo!");
        else if(score <= 800)
            return 100;
        else
            return 120;

    }

    private int getScore(){
        return new Random().nextInt(0, 1000 );
    }
}
