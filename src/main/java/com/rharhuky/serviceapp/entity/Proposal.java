package com.rharhuky.serviceapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valueRequired;

    private int deadLineInMonths;

    private Boolean approved;

    private boolean integrated;

    private String details;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User theUser;

}
