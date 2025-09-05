package com.contract.pdf.ContractPdf.DTO;

public record PersonRequestDTO (
        String name,
        String cpfCnpj,
        String email
) {
}