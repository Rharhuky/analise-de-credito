package com.rharhuky.notificationapp.domain;


import lombok.*;

@Setter
@Getter
public class Proposta {

    private Long id;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private boolean integrada;

    private String observacao;

    private User user;
}

