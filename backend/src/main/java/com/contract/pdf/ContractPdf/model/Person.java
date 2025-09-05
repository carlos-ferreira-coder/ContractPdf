package com.contract.pdf.ContractPdf.model;

import com.contract.pdf.ContractPdf.DTO.PersonRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "person", schema = "contract_pdf")
@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @Column(name = "email", nullable = false)
    private String email;

    public Person (PersonRequestDTO data) {
        this.name = data.name();
        this.cpfCnpj = data.cpfCnpj();
        this.email = data.email();
    }
}