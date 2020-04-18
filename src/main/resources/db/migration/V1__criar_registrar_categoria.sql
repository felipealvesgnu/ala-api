CREATE TABLE categoria(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Associado');
INSERT INTO categoria (nome) values ('Assistido');
INSERT INTO categoria (nome) values ('Parceiro');
