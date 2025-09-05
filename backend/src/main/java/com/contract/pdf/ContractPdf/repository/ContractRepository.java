package com.contract.pdf.ContractPdf.repository;

import com.contract.pdf.ContractPdf.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
}