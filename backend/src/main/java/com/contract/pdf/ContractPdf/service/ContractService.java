package com.contract.pdf.ContractPdf.service;

import com.contract.pdf.ContractPdf.DTO.*;
import com.contract.pdf.ContractPdf.model.*;
import com.contract.pdf.ContractPdf.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SignerRepository signerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractRequestRepository contractRequestRepository;

    public List<ContractResponseDTO> listContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(contract -> new ContractResponseDTO(
                        contract.getId(),
                        contract.getAmount(),
                        contract.getStartDate(),
                        contract.getDuration(),
                        contract.getCity(),
                        contract.getUf(),
                        contract.getDescription()
                ))
                .toList();
    }

    @Transactional
    public ContractResponseDTO createContract (CreateContractDTO contractDTO) {
        Person personContractor = new Person(contractDTO.contractor());
        Person personContractee = new Person(contractDTO.contractee());
        personRepository.save(personContractor);
        personRepository.save(personContractee);

        ContractRequest contractRequest = new ContractRequest();
        contractRequest.setStatus("PENDING");
        contractRequestRepository.save(contractRequest);

        Contract contract = new Contract(contractDTO.contract(), contractRequest);
        contractRepository.save(contract);

        Signer signerContractor = new Signer(SignerType.CONTRATANTE, personContractor, contractRequest);
        Signer signerContractee = new Signer(SignerType.CONTRATADO, personContractee, contractRequest);
        signerRepository.save(signerContractor);
        signerRepository.save(signerContractee);

        return new ContractResponseDTO(
                contract.getId(),
                contract.getAmount(),
                contract.getStartDate(),
                contract.getDuration(),
                contract.getCity(),
                contract.getUf(),
                contract.getDescription()
        );
    }

}
