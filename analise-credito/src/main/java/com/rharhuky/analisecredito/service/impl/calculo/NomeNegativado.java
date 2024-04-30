package com.rharhuky.analisecredito.service.impl.calculo;

import com.rharhuky.analisecredito.domain.Proposta;
import com.rharhuky.analisecredito.exceptions.NomeNegativadoException;
import com.rharhuky.analisecredito.service.interfaces.calculo.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Order(value = 1)
public class NomeNegativado implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
       if(isNegativado(proposta.getUser().getCpf()))
           throw new NomeNegativadoException();
       return 100;
    }

    private boolean isNegativado(String cpfCnpj){
        return new Random().nextBoolean();
    }

}
