package com.contract.pdf.ContractPdf.model;

import com.contract.pdf.ContractPdf.DTO.ContractRequestRequestDTO;
import com.contract.pdf.ContractPdf.DTO.SignerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "contract_request", schema = "contract_pdf")
@Entity(name = "contract_request")
public class ContractRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "pdf_path")
    private String pdfPath;

    public ContractRequest (ContractRequestRequestDTO contractRequestData) {
        this.createdAt = contractRequestData.createdAt();
        this.status = contractRequestData.status();
        this.pdfPath = contractRequestData.pdfPath();
    }
}
