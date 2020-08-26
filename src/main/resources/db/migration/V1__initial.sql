CREATE TABLE estado
(
    id   INT(2) auto_increment PRIMARY KEY,
    nome VARCHAR(25) NULL,
    uf   VARCHAR(2) NULL
);

CREATE TABLE cidade
(
    id        BIGINT(11) auto_increment PRIMARY KEY,
    nome      VARCHAR(120) NULL,
    estado_id INT NOT NULL,
    CONSTRAINT fk_cidade1
        FOREIGN KEY (estado_id) REFERENCES estado (id)
);

CREATE TABLE permissao
(
    id        BIGINT auto_increment PRIMARY KEY,
    descricao VARCHAR(50) DEFAULT '' NOT NULL
);

CREATE TABLE pessoa_fisica
(
    id                BIGINT auto_increment PRIMARY KEY,
    nome              VARCHAR(50) NULL,
    cpf               VARCHAR(11) NOT NULL,
    rg                VARCHAR(14) NULL,
    rg_org_emisor     VARCHAR(6) NULL,
    rg_uf             VARCHAR(2) NULL,
    data_nasc         DATE NULL,
    profissao         VARCHAR(50) NULL,
    email             VARCHAR(50) NULL,
    telefone          VARCHAR(15) NULL,
    tipo_pessoa       VARCHAR(10) NOT NULL,
    facebook          VARCHAR(45) NULL,
    instagram         VARCHAR(25) NULL,
    nome_conjuge      VARCHAR(50) NULL,
    data_nasc_conjuge DATE NULL,
    ativo             BIT NULL
);

CREATE TABLE atividade
(
    id               BIGINT auto_increment PRIMARY KEY,
    descricao        VARCHAR(150) NULL,
    palestrante      BIT NULL,
    pessoa_fisica_id BIGINT NOT NULL,
    CONSTRAINT fk_atividade_pessoa_fisica1
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica (id)
);

CREATE TABLE pessoa_fisica_endereco
(
    pessoa_fisica_id BIGINT NOT NULL,
    logradouro       VARCHAR(30) NULL,
    numero           VARCHAR(30) NULL,
    complemento      VARCHAR(30) NULL,
    bairro           VARCHAR(30) NULL,
    cep              VARCHAR(30) NULL,
    tipo             VARCHAR(12) NULL,
    cidade_id        BIGINT NOT NULL,
    CONSTRAINT fk_pessoa_fisica_endereco_cidade1
        FOREIGN KEY (cidade_id) REFERENCES cidade (id),
    CONSTRAINT fk_pessoa_fisica_endereco_pessoa_fisica1
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica (id)
);

CREATE TABLE pessoa_juridica
(
    id             BIGINT auto_increment PRIMARY KEY,
    nome           VARCHAR(45) NULL,
    cnpj           VARCHAR(14) NOT NULL,
    insc_estadual  VARCHAR(14) NULL,
    insc_municipal VARCHAR(14) NULL,
    email          VARCHAR(50) NULL,
    telefone       VARCHAR(10) NULL,
    tipo_pessoa    VARCHAR(10) NOT NULL,
    ativo          TINYINT NULL
);

CREATE TABLE pretensao_atividade
(
    id                  BIGINT auto_increment PRIMARY KEY,
    descricao           VARCHAR(100) NULL,
    interesse_palestrar BIT NULL,
    area_palestra       VARCHAR(80) NULL,
    pessoa_fisica_id    BIGINT NOT NULL,
    CONSTRAINT fk_pretensao_atividade_pessoa_fisica1
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica (id)
);

CREATE TABLE pretensao_mensalidade
(
    id               BIGINT auto_increment PRIMARY KEY,
    valor            DECIMAL(10, 2) NULL,
    pessoa_fisica_id BIGINT NOT NULL,
    CONSTRAINT fk_pretensao_mensalidade_pessoa_fisica1
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica (id)
);

CREATE TABLE tipo_pagamento
(
    id        INT auto_increment PRIMARY KEY,
    descricao VARCHAR(30) NOT NULL
);

CREATE TABLE mensalidade
(
    id                BIGINT(11) auto_increment PRIMARY KEY,
    valor             DECIMAL(10, 2) NULL,
    pessoa_fisica_id  BIGINT NOT NULL,
    tipo_pagamento_id INT NOT NULL,
    CONSTRAINT fk_mensalidade_pessoa_fisica1
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica (id),
    CONSTRAINT fk_mensalidade_tipo_pagamento1
        FOREIGN KEY (tipo_pagamento_id) REFERENCES tipo_pagamento (id)
);

CREATE TABLE usuario
(
    id    BIGINT auto_increment PRIMARY KEY,
    nome  VARCHAR(50) DEFAULT '' NOT NULL,
    email VARCHAR(50) DEFAULT '' NOT NULL,
    senha VARCHAR(150) DEFAULT '' NOT NULL
);

CREATE TABLE usuario_permissao
(
    usuario_id   BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, permissao_id),
    CONSTRAINT fk_usuario_permissao_permissao1
        FOREIGN KEY (permissao_id) REFERENCES permissao (id),
    CONSTRAINT fk_usuario_permissao_usuario1
        FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);