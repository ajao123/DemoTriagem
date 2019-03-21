CREATE TABLE triagem(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sintomas varchar(200),
    pontuacao int unsigned,
    data_triagem date
)ENGINE=InnoDB DEFAULT CHARSET=utf8;