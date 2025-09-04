package com.contract.pdf.ContractPdf.model;

import com.contract.pdf.ContractPdf.DTO.ContractRequestDTO;
import com.contract.pdf.ContractPdf.DTO.SignerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "contract", schema = "contract_pdf")
@Entity(name = "contract")
public class    Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contract_request_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_request"))
    private ContractRequest contractRequest;

    public Contract (ContractRequestDTO contractData, ContractRequest contractRequestEntity) {
        this.amount = contractData.amount();
        this.startDate = contractData.startDate();
        this.duration = contractData.duration();
        this.city = contractData.city();
        this.uf = contractData.uf();
        this.description = contractData.description();
        this.contractRequest = contractRequestEntity;
    }
}
