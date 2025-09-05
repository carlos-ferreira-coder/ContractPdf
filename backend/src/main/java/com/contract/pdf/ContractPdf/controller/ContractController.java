package com.contract.pdf.ContractPdf.controller;

import com.contract.pdf.ContractPdf.DTO.ContractRequestRequestDTO;
import com.contract.pdf.ContractPdf.DTO.ContractResponseDTO;
import com.contract.pdf.ContractPdf.service.ClicksignService;
import com.contract.pdf.ContractPdf.service.ItextService;
import com.contract.pdf.ContractPdf.service.PostgresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {

    private final PostgresService postgresService;
    private final ItextService itextService;
    private final ClicksignService clicksignService;

    public ContractController(
            PostgresService postgresService,
            ItextService itextService,
            ClicksignService clicksignService) {
        this.postgresService = postgresService;
        this.itextService = itextService;
        this.clicksignService = clicksignService;
    }

    @Transactional
    @PostMapping("/contract/create")
    public ResponseEntity<ContractResponseDTO> createEnvelope(@RequestBody ContractRequestRequestDTO dto) throws Exception {
        // Clicksign
        String nameEnvelope = "Contrato - " + dto.contractor().name();
        String envelopeId = clicksignService.createEnvelope(nameEnvelope);

        String signerContractorId = clicksignService.addSigner(envelopeId, dto.contractor());
        String signerContracteeId = clicksignService.addSigner(envelopeId, dto.contractee());

        String fileName = "Contrato_" + dto.contractor().name().replaceAll("\\s+", "_") + ".pdf";
        byte[] pdfBytes = itextService.generatePdf(dto.contractor(), dto.contractee(), dto.contract());
        String documentId = clicksignService.addDocument(envelopeId, pdfBytes, fileName);

        // Postgres
        ContractResponseDTO response = postgresService.createContract(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
