package com.rharhuky.serviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalResponse {

    private Long id;

    private String name;

    private String lastName;

    private String cpf;

    private String contact;

    private Double renda;

    private Double valueRequired;

    private int deadLineInMonths;

    private boolean approved;

    private String observation;
}
