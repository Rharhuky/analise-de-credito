package com.rharhuky.analisecredito.exceptions;

import com.rharhuky.analisecredito.annotations.AnaliseException;

@AnaliseException(value = "${app.messages.exceptions.nome-sujo}")
public class NomeNegativadoException extends RuntimeException{
}
