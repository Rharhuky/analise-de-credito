package com.rharhuky.serviceapp.repository;

import com.rharhuky.serviceapp.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposta, Long> {
}
