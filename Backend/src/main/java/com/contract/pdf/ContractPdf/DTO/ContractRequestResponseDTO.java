package com.contract.pdf.ContractPdf.DTO;

import com.contract.pdf.ContractPdf.model.ContractRequest;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ContractRequestResponseDTO(
        UUID id,
        OffsetDateTime createdAt,
        String status,
        String pdfPath
) {
    public ContractRequestResponseDTO (ContractRequest contractRequest) {
        this(
                contractRequest.getId(),
                contractRequest.getCreatedAt(),
                contractRequest.getStatus(),
                contractRequest.getPdfPath()
        );
    }
}
