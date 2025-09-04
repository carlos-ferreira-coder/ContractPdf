package com.contract.pdf.ContractPdf.repository;

import com.contract.pdf.ContractPdf.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
