package com.contract.pdf.ContractPdf.DTO;

public record CreateContractDTO(
        PersonRequestDTO contractor,
        PersonRequestDTO contractee,
        ContractRequestDTO contract
) {
}
