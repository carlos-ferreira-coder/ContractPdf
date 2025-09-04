package com.contract.pdf.ContractPdf.DTO;

import com.contract.pdf.ContractPdf.model.Person;

import java.util.UUID;

public record PersonResponseDTO (
        UUID id,
        String name,
        String cpfCnpj,
        String email
) {
    public PersonResponseDTO (Person person) {
        this(
                person.getId(),
                person.getName(),
                person.getCpfCnpj(),
                person.getEmail()
        );
    }
}
