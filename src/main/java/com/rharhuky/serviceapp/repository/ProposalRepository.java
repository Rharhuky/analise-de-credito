package com.rharhuky.serviceapp.repository;

import com.rharhuky.serviceapp.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findAllByIntegradoIsFalse();
}
