package com.contract.pdf.ContractPdf.service;


import com.contract.pdf.ContractPdf.DTO.ContractRequestDTO;
import com.contract.pdf.ContractPdf.DTO.PersonRequestDTO;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ItextService {

    public byte[] generatePdf(PersonRequestDTO contractor, PersonRequestDTO contractee, ContractRequestDTO contract) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            document.add(new Paragraph("CONTRATO DE PRESTAÇÃO DE SERVIÇOS")
                    .setFontSize(16)
                    .setMarginBottom(20));

            // Contractor
            document.add(new Paragraph(new Text("Contratante: "))
                    .add(contractor.name() + " - CPF/CNPJ: " + contractor.cpfCnpj()));
            document.add(new Paragraph("E-mail: " + contractor.email()));
            document.add(new Paragraph("\n"));

            // Contractee
            document.add(new Paragraph(new Text("Contratado: "))
                    .add(contractee.name() + " - CPF/CNPJ: " + contractee.cpfCnpj()));
            document.add(new Paragraph("E-mail: " + contractee.email()));
            document.add(new Paragraph("\n"));

            // Contract
            document.add(new Paragraph("Valor: R$ " + contract.amount()));
            document.add(new Paragraph("Data de início: " + contract.startDate()));
            document.add(new Paragraph("Prazo (meses): " + contract.duration()));
            document.add(new Paragraph("Cidade/UF: " + contract.cityUf()));
            document.add(new Paragraph("Descrição: " + contract.description()));

            document.add(new Paragraph("\n\nAs partes concordam com os termos acima."));

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar contrato PDF", e);
        }
    }
}
