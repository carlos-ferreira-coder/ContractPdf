CREATE SCHEMA contract_pdf;
SET search_path TO contract_pdf;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE contract_request (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(50) NOT NULL,
    pdf_path VARCHAR(255),
);

CREATE TABLE contract (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount NUMERIC(12,2) NOT NULL,
    start_date DATE NOT NULL,
    duration INT NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    description TEXT NOT NULL,
    request_id UUID NOT NULL,
    CONSTRAINT fk_contract_request FOREIGN KEY (request_id)
            REFERENCES contract_request (id) ON DELETE CASCADE
);

CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE signer (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(20) NOT NULL CHECK (type IN ('CONTRATANTE', 'CONTRATADO')),
    person_id UUID NOT NULL,
    contract_request_id UUID NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id)
        REFERENCES person (id) ON DELETE CASCADE,
    CONSTRAINT fk_contract_request FOREIGN KEY (contract_request_id)
        REFERENCES contract_request (id) ON DELETE CASCADE
);

CREATE INDEX idx_contract_request_status ON contract_request (status);
CREATE INDEX idx_person_email ON person (email);
CREATE INDEX idx_person_cpf_cnpj ON person (cpf_cnpj);