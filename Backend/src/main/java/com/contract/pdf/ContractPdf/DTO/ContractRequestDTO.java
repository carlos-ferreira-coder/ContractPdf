package com.contract.pdf.ContractPdf.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractRequestDTO(
        BigDecimal amount,
        LocalDate startDate,
        Integer duration,
        String city,
        String uf,
        String description
) {
}
