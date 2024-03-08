package com.rharhuky.serviceapp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaRequestDto {

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

    private Double valorSolicitado;

    private int prazoPagamento;
}
