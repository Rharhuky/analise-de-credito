package com.rharhuky.serviceapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String cpf;
    private String contact;
    private Double renda;

    @OneToOne(mappedBy = "theUser")
    private Proposal proposal;

}
