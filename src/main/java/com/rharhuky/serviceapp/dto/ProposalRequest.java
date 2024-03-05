package com.rharhuky.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalRequest {

    private String name;

    private String lastName;

    private String cpf;

    private String contact;

    private Double income;

    private Double valueRequired;

    private int deadLineInMonths;
}
