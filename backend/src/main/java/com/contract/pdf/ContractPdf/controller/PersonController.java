package com.contract.pdf.ContractPdf.controller;

import com.contract.pdf.ContractPdf.DTO.PersonRequestDTO;
import com.contract.pdf.ContractPdf.DTO.PersonResponseDTO;
import com.contract.pdf.ContractPdf.model.Person;
import com.contract.pdf.ContractPdf.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<PersonResponseDTO> getAll() {
        return personRepository.findAll().stream().map(PersonResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public PersonResponseDTO getPersonById (@PathVariable("id") UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa " + id + "não encontrada! "));
        return new PersonResponseDTO(person);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void savePerson (@RequestBody PersonRequestDTO data) {
        Person person = new Person(data);

        personRepository.save(person);

        return;
    }
}
