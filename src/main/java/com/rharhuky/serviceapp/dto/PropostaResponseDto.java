package com.rharhuky.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDto {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

    private String valorSolicitadoFmt;

    private int prazoPagamento;

    private boolean aprovado;

    private String observacao;
}
