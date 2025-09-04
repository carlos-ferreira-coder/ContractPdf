package com.contract.pdf.ContractPdf.DTO;

import java.time.OffsetDateTime;

public record ContractRequestRequestDTO(
        OffsetDateTime createdAt,
        String status,
        String pdfPath
) {
}
