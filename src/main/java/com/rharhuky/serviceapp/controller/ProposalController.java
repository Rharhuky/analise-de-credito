package com.rharhuky.serviceapp.controller;

import com.rharhuky.serviceapp.dto.ProposalRequest;
import com.rharhuky.serviceapp.dto.ProposalResponse;
import com.rharhuky.serviceapp.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/proposal")
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponse> create(@RequestBody ProposalRequest proposalRequest) {
        ProposalResponse proposalResponse = proposalService.create(proposalRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .buildAndExpand(proposalResponse.getId())
                        .toUri())
                .body(proposalResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProposalResponse>> getAll(){
        return ResponseEntity.ok().body(this.proposalService.findAll());

    }
}
