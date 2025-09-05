package com.contract.pdf.ContractPdf.model;

import com.contract.pdf.ContractPdf.DTO.SignerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "signer", schema = "contract_pdf")
@Entity(name = "signer")
public class Signer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SignerType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "fk_person"))
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contract_request_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_request"))
    private ContractRequest contractRequest;

    public Signer (SignerType type, Person personEntity, ContractRequest contractRequestEntity) {
        this.type = type;
        this.person = personEntity;
        this.contractRequest = contractRequestEntity;
    }
}
