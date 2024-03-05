package com.rharhuky.serviceapp.rempository;

import com.rharhuky.serviceapp.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
