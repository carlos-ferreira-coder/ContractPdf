package com.contract.pdf.ContractPdf.repository;

import com.contract.pdf.ContractPdf.model.ContractRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRequestRepository extends JpaRepository<ContractRequest, UUID> {
}