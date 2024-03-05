package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.ProposalRequest;
import com.rharhuky.serviceapp.dto.ProposalResponse;
import com.rharhuky.serviceapp.mapper.ProposalMapper;
import com.rharhuky.serviceapp.rempository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public ProposalResponse create(ProposalRequest proposalRequest){
        var theProposal = ProposalMapper.CONVERT.convertToProposal(proposalRequest);
        return ProposalMapper.CONVERT.convertToProposalResponse(proposalRepository.save(theProposal));
    }

}
