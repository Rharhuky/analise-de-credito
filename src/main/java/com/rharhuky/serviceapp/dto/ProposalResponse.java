package com.rharhuky.serviceapp.dto;

import lombok.*;

@EqualsAndHashCode
@ToString
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

    private Double income;

    private Double valueRequired;

    private int deadLineInMonths;

    private boolean approved;

    private String details;
}
