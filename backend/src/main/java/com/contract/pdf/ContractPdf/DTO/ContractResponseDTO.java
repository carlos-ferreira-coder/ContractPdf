package com.contract.pdf.ContractPdf.DTO;

import com.contract.pdf.ContractPdf.model.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ContractResponseDTO(
        UUID id,
        BigDecimal amount,
        LocalDate startDate,
        Integer duration,
        String cityUf,
        String description
) {
    public ContractResponseDTO (Contract contract) {
        this(
                contract.getId(),
                contract.getAmount(),
                contract.getStartDate(),
                contract.getDuration(),
                contract.getCityUf(),
                contract.getDescription()
        );
    }
}
