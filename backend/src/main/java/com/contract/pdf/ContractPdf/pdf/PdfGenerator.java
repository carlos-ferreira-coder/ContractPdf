package com.contract.pdf.ContractPdf.pdf;

import com.contract.pdf.ContractPdf.model.Contract;
import com.contract.pdf.ContractPdf.model.Person;

import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfGenerator {
    public static byte[] generate(Contract contract, Person contractor, Person contractee) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfDocument pdf = new PdfDocument(new PdfWriter(baos));
             Document document = new Document(pdf)) {

            document.add(new Paragraph("CONTRATO DE PRESTAÇÃO DE SERVIÇOS"));
            document.add(new Paragraph("Contratante: " + contractor.getName()));
            document.add(new Paragraph("Contratado: " + contractee.getName()));
            document.add(new Paragraph("Detalhes do contrato: " + contract.getDescription()));

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return baos.toByteArray();
    }
}
