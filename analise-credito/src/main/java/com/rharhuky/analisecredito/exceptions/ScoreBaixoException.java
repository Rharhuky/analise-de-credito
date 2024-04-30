package com.rharhuky.analisecredito.exceptions;

import com.rharhuky.analisecredito.annotations.AnaliseException;

@AnaliseException(value = "${app.messages.exceptions.score-baixo}")
public class ScoreBaixoException extends RuntimeException{
}
