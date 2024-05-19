package com.rharhuky.serviceapp.mapper;

import com.rharhuky.serviceapp.dto.PropostaRequestDto;
import com.rharhuky.serviceapp.dto.PropostaResponseDto;
import com.rharhuky.serviceapp.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper CONVERT = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "user.nome", source = "nome")
    @Mapping(target = "user.sobrenome", source = "sobrenome")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.telefone", source = "telefone")
    @Mapping(target = "user.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    @Mapping(target = "observacao", ignore = true)
    Proposta convertToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "user.nome")
    @Mapping(target = "sobrenome", source = "user.sobrenome")
    @Mapping(target = "telefone", source = "user.telefone")
    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "renda", source = "user.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponseDto convertToPropostaResponseDto(Proposta proposta);

    List<PropostaResponseDto> convertListToListResponse(List<Proposta> propostas);

    default String setValorSolicitadoFmt(Proposta proposta){
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }
}
