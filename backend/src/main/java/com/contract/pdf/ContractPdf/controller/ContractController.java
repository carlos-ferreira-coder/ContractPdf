package com.contract.pdf.ContractPdf.controller;

import com.contract.pdf.ContractPdf.DTO.ContractRequestDTO;
import com.contract.pdf.ContractPdf.DTO.ContractResponseDTO;
import com.contract.pdf.ContractPdf.DTO.CreateContractDTO;
import com.contract.pdf.ContractPdf.model.*;
import com.contract.pdf.ContractPdf.service.ContractService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController (ContractService contractService) {
        this.contractService = contractService;
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<ContractResponseDTO> create (@RequestBody CreateContractDTO dto) {
        ContractResponseDTO response = contractService.createContract(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ContractResponseDTO>> listAll() {
        List<ContractResponseDTO> response = contractService.listContracts();
        return ResponseEntity.ok(response);
    }
}
