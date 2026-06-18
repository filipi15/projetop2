CREATE TABLE IF NOT EXISTS users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    artista VARCHAR(150) NOT NULL,
    album VARCHAR(150) NOT NULL,
    formato VARCHAR(30) NOT NULL,
    genero VARCHAR(60),
    preco VARCHAR(20) NOT NULL,
    ano VARCHAR(4),
    imagem VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS perfil (
    id SERIAL PRIMARY KEY,
    userid UUID NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (userid) REFERENCES users(id),
    CONSTRAINT perfil_unique UNIQUE (userid)
);