DROP DATABASE IF EXISTS sistema_academico;
CREATE DATABASE sistema_academico;
USE sistema_academico;

CREATE TABLE aluno (

    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    rgm VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    endereco VARCHAR(200),
    municipio VARCHAR(100),
    uf VARCHAR(2),
    celular VARCHAR(20)
);

CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    rgm VARCHAR(20) NOT NULL UNIQUE,
    curso VARCHAR(100) NOT NULL,
    campus VARCHAR(100),
    periodo VARCHAR(30),
    CONSTRAINT fk_curso_aluno
    FOREIGN KEY (rgm)
    REFERENCES aluno(rgm)
    ON DELETE CASCADE
);

CREATE TABLE nota_falta (
    id_nota_falta INT AUTO_INCREMENT PRIMARY KEY,
    rgm VARCHAR(20) NOT NULL,
    disciplina VARCHAR(100) NOT NULL,
    semestre VARCHAR(20) NOT NULL,
    a1 DECIMAL(4,2),
    a2 DECIMAL(4,2),
    af DECIMAL(4,2),
    media DECIMAL(4,2),
    faltas INT,
    situacao VARCHAR(50),
    CONSTRAINT fk_nota_aluno
    FOREIGN KEY (rgm)
    REFERENCES aluno(rgm)
    ON DELETE CASCADE,

    CONSTRAINT uk_nota_unica
    UNIQUE (rgm, disciplina, semestre)
);