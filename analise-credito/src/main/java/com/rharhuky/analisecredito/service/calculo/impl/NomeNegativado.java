package com.rharhuky.analisecredito.service.calculo.impl;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.service.calculo.CalculoPonto;

import java.util.Random;

public class NomeNegativado implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
       if(isNegativado(proposta.getUser().getCpf()))
           throw new RuntimeException("Nome Sujo");
       return 100;
    }

    private boolean isNegativado(String cpfCnpj){
        return new Random().nextBoolean();
    }

}
