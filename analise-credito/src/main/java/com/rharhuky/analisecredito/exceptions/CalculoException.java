package com.rharhuky.analisecredito.exceptions;

import com.rharhuky.analisecredito.annotations.AnaliseException;

@AnaliseException(value = "exceptions.calculo")
public class CalculoException extends RuntimeException {
}
