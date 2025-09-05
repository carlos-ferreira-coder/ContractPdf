package com.contract.pdf.ContractPdf.repository;

import com.contract.pdf.ContractPdf.model.Signer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignerRepository extends JpaRepository<Signer, UUID> {
}