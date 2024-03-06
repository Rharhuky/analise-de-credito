package com.rharhuky.serviceapp.mapper;

import com.rharhuky.serviceapp.dto.ProposalRequest;
import com.rharhuky.serviceapp.dto.ProposalResponse;
import com.rharhuky.serviceapp.entity.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProposalMapper {

    ProposalMapper CONVERT = Mappers.getMapper(ProposalMapper.class);

    @Mapping(target = "theUser.name", source = "name")
    @Mapping(target = "theUser.lastName", source = "lastName")
    @Mapping(target = "theUser.cpf", source = "cpf")
    @Mapping(target = "theUser.contact", source = "contact")
    @Mapping(target = "theUser.income", source = "income")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    @Mapping(target = "details", ignore = true)
    Proposal convertToProposal(ProposalRequest proposalRequest);

    @Mapping(target = "name", source = "theUser.name")
    @Mapping(target = "lastName", source = "theUser.lastName")
    @Mapping(target = "contact", source = "theUser.contact")
    @Mapping(target = "cpf", source = "theUser.cpf")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "valueRequired", source = "valueRequired")
    @Mapping(target = "deadLineInMonths", source = "deadLineInMonths")
    @Mapping(target = "approved", source = "approved")
    @Mapping(target = "details", source = "details")
    @Mapping(target = "income", source = "theUser.income")
    ProposalResponse convertToProposalResponse(Proposal proposal);

    List<ProposalResponse> convertListToListResponse(List<Proposal> proposals);
}
