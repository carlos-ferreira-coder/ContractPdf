package com.contract.pdf.ContractPdf.service;

import com.contract.pdf.ContractPdf.DTO.PersonRequestDTO;
import com.contract.pdf.ContractPdf.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Base64;

@Service
public class ClicksignService {

    private final String apiUrl;
    private final String apiToken;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ClicksignService(
            @Value("${clicksign.api.url}") String apiUrl,
            @Value("${clicksign.api.token}") String apiToken
    ) {
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    public String createEnvelope (String nameEnvelope) throws IOException {
        MediaType mediaType = MediaType.parse("application/vnd.api+json");

        String payload = """
                {
                  "data": {
                    "type": "envelopes",
                    "attributes": {
                      "name": "%s",
                      "locale": "pt-BR",
                      "auto_close": true,
                      "remind_interval": "3",
                      "block_after_refusal": false
                    }
                  }
                }
                """.formatted(StringUtils.sanitize(nameEnvelope));

        RequestBody body = RequestBody.create(payload.getBytes(), mediaType);

        Request request = new Request.Builder()
                .url(apiUrl + "/envelopes")
                .post(body)
                .addHeader("accept", "application/vnd.api+json")
                .addHeader("Content-Type", "application/vnd.api+json")
                .addHeader("Authorization", apiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Erro ao criar envelope: " + response.code() + " - " + responseBody);
            }

            JsonNode json = objectMapper.readTree(responseBody);
            return json.path("data").path("id").asText();
        }

    }

    @Transactional
    public String addSigner (String envelopeId, PersonRequestDTO signer) throws IOException {
        MediaType mediaType = MediaType.parse("application/vnd.api+json");

        String payload = """
        {
          "data": {
            "type": "signers",
            "attributes": {
              "name": "%s",
              "email": "%s",
              "documentation": "%s",
              "has_documentation": true,
              "group": 1,
              "location_required_enabled": false,
              "communicate_events": {
                "signature_request": "email",
                "signature_reminder": "email",
                "document_signed": "email"
              }
            }
          }
        }
    """.formatted(
                StringUtils.sanitize(signer.name()),
                signer.email(),
                StringUtils.sanitize(signer.cpfCnpj())
        );

        RequestBody body = RequestBody.create(payload.getBytes(), mediaType);

        Request request = new Request.Builder()
                .url(apiUrl + "/envelopes/" + envelopeId + "/signers")
                .post(body)
                .addHeader("accept", "application/vnd.api+json")
                .addHeader("Content-Type", "application/vnd.api+json")
                .addHeader("Authorization", apiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Erro ao adicionar signatário: " + response.code() + " - " + responseBody);
            }

            JsonNode json = objectMapper.readTree(responseBody);
            return json.path("data").path("id").asText();
        }

    }

    @Transactional
    public String addDocument(String envelopeId, byte[] pdfBytes, String fileName) throws IOException {
        MediaType mediaType = MediaType.parse("application/vnd.api+json");

        String payload = """
        {
          "data": {
            "type": "documents",
            "attributes": {
              "file_name": "%s",
              "file_base64": "%s"
            }
          }
        }
    """.formatted(
                StringUtils.sanitize(fileName),
                Base64.getEncoder().encodeToString(pdfBytes)
        );

        RequestBody body = RequestBody.create(payload.getBytes(), mediaType);

        Request request = new Request.Builder()
                .url(apiUrl + "/envelopes/" + envelopeId + "/documents")
                .post(body)
                .addHeader("Accept", "application/vnd.api+json")
                .addHeader("Content-Type", "application/vnd.api+json")
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Erro ao adicionar documento: " + response.code() + " - " + responseBody);
            }

            JsonNode json = objectMapper.readTree(responseBody);
            return json.path("data").path("id").asText();
        }
    }

}
