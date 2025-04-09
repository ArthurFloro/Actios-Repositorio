
-- Tabela: Usuario
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    tipo_usuario ENUM('estudante', 'organizador', 'admin')
);

-- Tabela: Faculdade
CREATE TABLE Faculdade (
    id_faculdade INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(100)
);

-- Tabela: Curso
CREATE TABLE Curso (
    id_curso INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    area_academica VARCHAR(100)
);

-- Tabela: VinculoCursoUsuario
CREATE TABLE VinculoCursoUsuario (
    id_usuario INT,
    id_curso INT,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_curso) REFERENCES Curso(id_curso)
);

-- Tabela: Categoria
CREATE TABLE Categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

-- Tabela: Evento
CREATE TABLE Evento (
    id_evento INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    formato ENUM('online', 'presencial'),
    certificado BOOLEAN,
    valor DECIMAL(10,2),
    id_faculdade INT,
    id_categoria INT,
    FOREIGN KEY (id_faculdade) REFERENCES Faculdade(id_faculdade),
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

-- Tabela: Participacao
CREATE TABLE Participacao (
    id_participacao INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_evento INT,
    checkin BOOLEAN,
    feedback TEXT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES Evento(id_evento)
);

-- Tabela: Palestrante
CREATE TABLE Palestrante (
    id_palestrante INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    mini_bio TEXT,
    id_evento INT,
    FOREIGN KEY (id_evento) REFERENCES Evento(id_evento)
);

-- Tabela: Organizador
CREATE TABLE Organizador (
    id_organizador INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    id_faculdade INT,
    FOREIGN KEY (id_faculdade) REFERENCES Faculdade(id_faculdade)
);

-- Tabela: Notificacao
CREATE TABLE Notificacao (
    id_notificacao INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_evento INT,
    data_envio TIMESTAMP,
    mensagem TEXT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES Evento(id_evento)
);

-- Tabela: RegistroCertificado
CREATE TABLE RegistroCertificado (
    id_certificado INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_evento INT,
    data_emissao DATE,
    codigo_validacao VARCHAR(50) UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES Evento(id_evento)
);

-- Tabela: FeedbackEvento
CREATE TABLE FeedbackEvento (
    id_feedback INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_evento INT,
    nota INT CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    data_feedback DATETIME,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES Evento(id_evento)
);

-- Stored Procedure: EmitirCertificado
DELIMITER //

CREATE PROCEDURE EmitirCertificado(
  IN p_id_usuario INT,
  IN p_id_evento INT,
  IN p_codigo_validacao VARCHAR(50)
)
BEGIN
  INSERT INTO RegistroCertificado (id_usuario, id_evento, data_emissao, codigo_validacao)
  VALUES (p_id_usuario, p_id_evento, NOW(), p_codigo_validacao);
END;
//

-- Stored Procedure: FazerCheckin
CREATE PROCEDURE FazerCheckin(
  IN p_id_usuario INT,
  IN p_id_evento INT
)
BEGIN
  UPDATE Participacao
  SET checkin = TRUE
  WHERE id_usuario = p_id_usuario AND id_evento = p_id_evento;
END;
//

-- Stored Procedure: EventosPorCategoria
CREATE PROCEDURE EventosPorCategoria(
  IN p_id_categoria INT
)
BEGIN
  SELECT nome, data_inicio, formato
  FROM Evento
  WHERE id_categoria = p_id_categoria;
END;
//

DELIMITER ;

 -- teste para ver se os dados podem ser inseridos 
INSERT INTO Usuario (nome, email, senha, tipo_usuario)
VALUES ('João da Silva', 'joao@email.com', 'senha123', 'estudante');

-- Inserir uma categoria (necessária para o evento)
INSERT INTO Categoria (nome) VALUES ('Tecnologia');

-- Inserir uma faculdade (necessária para o evento)
INSERT INTO Faculdade (nome, cidade, estado)
VALUES ('Faculdade XYZ', 'São Paulo', 'SP');

-- Inserir um evento
INSERT INTO Evento (nome, descricao, data_inicio, data_fim, formato, certificado, valor, id_faculdade, id_categoria)
VALUES (
  'Semana Acadêmica', 'Palestras e minicursos', '2025-06-01', '2025-06-05',
  'presencial', true, 0.00, 1, 1
);

-- Verificar os ids inseridos 
SELECT * FROM Usuario;
SELECT * FROM Evento;

-- chamando a procedure com call

CALL EmitirCertificado(1, 1, 'CERTIF-ABC123');

-- verificar se o certificado foi emitido

SELECT * FROM RegistroCertificado;


