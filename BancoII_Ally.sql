-- Exemplos citados na apresentação de priscila 

create database exemplo;
use exemplo;


CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    curso VARCHAR(100),
    faculdade_id INT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (faculdade_id) REFERENCES faculdades(id_faculdade)
);

 
CREATE TABLE palestrantes (
    id_palestrante INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    biografia TEXT
);
 
CREATE TABLE faculdades (
    id_faculdade INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    localizacao VARCHAR(255),
    site VARCHAR(255)
);
 
CREATE TABLE eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio DATETIME NOT NULL,
    data_fim DATETIME NOT NULL,
    local VARCHAR(255),
    categoria VARCHAR(100),
    faculdade_id INT,
    FOREIGN KEY (faculdade_id) REFERENCES faculdades(id_faculdade)
);
 
CREATE TABLE inscricoes (
    id_inscricao INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    evento_id INT,
    numero_inscricao VARCHAR(50) UNIQUE NOT NULL,
    data_inscricao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (evento_id) REFERENCES eventos(id_evento)
);
 
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL
);
