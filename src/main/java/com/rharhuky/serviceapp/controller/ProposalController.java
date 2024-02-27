package com.rharhuky.serviceapp.controller;

import com.rharhuky.serviceapp.dto.ProposalRequest;
import com.rharhuky.serviceapp.dto.ProposalResponse;
import com.rharhuky.serviceapp.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/proposals")
public class ProposalController {

    private ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponse> create(@RequestBody ProposalRequest proposalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.proposalService.create(proposalRequest));

    }

}
