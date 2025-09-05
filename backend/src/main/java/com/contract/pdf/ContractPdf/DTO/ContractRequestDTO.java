package com.contract.pdf.ContractPdf.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractRequestDTO(
        BigDecimal amount,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        Integer duration,
        String city,
        String uf,
        String description
) {
}
