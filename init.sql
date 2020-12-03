CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    data_criacao datetime NOT NULL,
    data_atualizacao datetime NOT NULL
);

CREATE TABLE conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cliente_id BIGINT UNIQUE NOT NULL,
    numero varchar(5) UNIQUE NOT NULL,
    saldo decimal NOT NULL,
    data_criacao datetime NOT NULL,
    data_atualizacao datetime NOT NULL
);

ALTER TABLE conta ADD FOREIGN KEY (cliente_id) REFERENCES cliente (id);