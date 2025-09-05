package com.contract.pdf.ContractPdf.service;

import com.contract.pdf.ContractPdf.DTO.ContractRequestRequestDTO;
import com.contract.pdf.ContractPdf.DTO.ContractResponseDTO;
import com.contract.pdf.ContractPdf.model.*;
import com.contract.pdf.ContractPdf.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostgresService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SignerRepository signerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractRequestRepository contractRequestRepository;

    @Transactional
    public ContractResponseDTO createContract (ContractRequestRequestDTO contractDTO) {
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
                contract.getCityUf(),
                contract.getDescription()
        );
    }

}