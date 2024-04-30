package com.rharhuky.analisecredito.exceptions;

import com.rharhuky.analisecredito.annotations.AnaliseException;

@AnaliseException(value = "${messages.exceptions.calculo}")
public class CalculoException extends RuntimeException {



}
