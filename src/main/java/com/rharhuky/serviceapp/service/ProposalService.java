package com.rharhuky.serviceapp.service;

import com.rharhuky.serviceapp.dto.PropostaRequestDto;
import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import com.rharhuky.serviceapp.mapper.PropostaMapper;
import com.rharhuky.serviceapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public PropostaResponseDto create(PropostaRequestDto propostaRequestDto){
        var theProposal = PropostaMapper.CONVERT.convertToProposta(propostaRequestDto);
        return PropostaMapper.CONVERT.convertToPropostaResponseDto(proposalRepository.save(theProposal));
    }

    public List<PropostaResponseDto> findAll(){
        return PropostaMapper.CONVERT.convertListToListResponse(this.proposalRepository.findAll());
    }

}
