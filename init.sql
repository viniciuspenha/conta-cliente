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

CREATE TABLE transferencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    conta_origem BIGINT NOT NULL,
    conta_destino BIGINT,
    numero_conta_destino varchar(5) NOT NULL,
    valor DECIMAL NOT NULL,
    status VARCHAR(9) NOT NULL DEFAULT 'REALIZADA' COMMENT 'REALIZADA, FALHA',
    erro VARCHAR (28) COMMENT 'SALDO_INSUFICIENTE, CONTA_DESTINO_NAO_ENCONTRADA, ERRO_INESPERADO',
    data_criacao DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL
);

ALTER TABLE conta ADD FOREIGN KEY (cliente_id) REFERENCES cliente (id);
ALTER TABLE transferencia ADD FOREIGN KEY (conta_origem) REFERENCES conta (id);
ALTER TABLE transferencia ADD FOREIGN KEY (conta_destino) REFERENCES conta (id);