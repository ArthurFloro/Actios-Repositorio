
create table curso (
id_curso int primary key auto_increment,
nome varchar(100) not null,
area_academica varchar(100)
);

create table VinculoCursoUsuario (
id_usuario int,
id_curso int,
primary key(id_usuario, id_curso),
foreign key(id_usuario) references usuario(id_usuario),
foreign key(id_curso) references curso(id_curso)
);

create table RegistroCertificado (
id_certificado int primary key auto_increment,
id_usuario int,
id_curso int,
data_emissao date,
codigo_validacao varchar(100) UNIQUE,
foreign key (id_usuario) references usuario(id_usuario),
foreign key (id_curso) references curso(id_curso)
);

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