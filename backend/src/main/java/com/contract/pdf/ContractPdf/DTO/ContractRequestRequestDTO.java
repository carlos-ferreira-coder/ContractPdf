package com.contract.pdf.ContractPdf.DTO;

public record ContractRequestRequestDTO (
        PersonRequestDTO contractor,
        PersonRequestDTO contractee,
        ContractRequestDTO contract
) {
}
