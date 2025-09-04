package com.contract.pdf.ContractPdf.controller;

import com.contract.pdf.ContractPdf.DTO.ContractRequestRequestDTO;
import com.contract.pdf.ContractPdf.DTO.ContractRequestResponseDTO;
import com.contract.pdf.ContractPdf.model.ContractRequest;
import com.contract.pdf.ContractPdf.repository.ContractRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("contract-request")
public class ContractRequestController {

    @Autowired
    private ContractRequestRepository contractRequestRepository;

    @GetMapping
    public List<ContractRequestResponseDTO> getAll() {
        return contractRequestRepository.findAll().stream().map(ContractRequestResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ContractRequestResponseDTO getContractById (@PathVariable("id") UUID id) {
        ContractRequest contractRequest = contractRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisição de contrato " + id + "não encontrado! "));
        return new ContractRequestResponseDTO(contractRequest);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveContractRequest (@RequestBody ContractRequestRequestDTO data) {
        ContractRequest contractRequest = new ContractRequest(data);

        contractRequestRepository.save(contractRequest);

        return;
    }
}
