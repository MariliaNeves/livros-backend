





create table autor (
    id integer generated always as identity primary key,
    nome varchar(40) not null
);

INSERT INTO autor (nome) VALUES ('Machado de Assis');
INSERT INTO autor (nome) VALUES ('Clarice Lispector');
INSERT INTO autor (nome) VALUES ('J. K. Rowling');
INSERT INTO autor (nome) VALUES ('George Orwell');
INSERT INTO autor (nome) VALUES ('J. R. R. Tolkien');

create table assunto (
    id integer generated always as identity primary key,
    descricao varchar(20) not null
);

INSERT INTO assunto (descricao) VALUES ('Romance');
INSERT INTO assunto (descricao) VALUES ('Fantasia');
INSERT INTO assunto (descricao) VALUES ('Ficcao');
INSERT INTO assunto (descricao) VALUES ('Drama');
INSERT INTO assunto (descricao) VALUES ('Aventura');

create table livro (
    id integer generated always as identity primary key,
    titulo varchar(40) not null,
    editora varchar(40) not null,
    edicao integer not null,
    ano_publicacao varchar(4) not null,
    valor decimal(12, 2) not null
);

create table livro_autor (
    livro_id integer not null,
    autor_id integer not null,
    primary key (livro_id, autor_id),
    constraint fk_livro_autor_livro foreign key (livro_id) references livro (id),
    constraint fk_livro_autor_autor foreign key (autor_id) references autor (id)
);

create table  livro_assunto (
    livro_id integer not null,
    assunto_id integer not null,
    primary key (livro_id, assunto_id),
    constraint fk_livro_assunto_livro foreign key (livro_id) references livro (id),
    constraint fk_livro_assunto_assunto foreign key (assunto_id) references assunto (id)
);

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor)
VALUES ('Dom Casmurro', 'Editora A', 1, '1899', 39.90);

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor)
VALUES ('A Hora da Estrela', 'Editora B', 2, '1977', 29.50);

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor)
VALUES ('Harry Potter', 'Rocco', 1, '1997', 59.90);

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor)
VALUES ('1984', 'Companhia das Letras', 3, '1949', 45.00);

INSERT INTO livro (titulo, editora, edicao, ano_publicacao, valor)
VALUES ('O Hobbit', 'HarperCollins', 2, '1937', 49.90);


INSERT INTO livro_autor (livro_id, autor_id) VALUES (1, 1);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (2, 2);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (3, 3);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (4, 4);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (5, 5);


INSERT INTO livro_assunto (livro_id, assunto_id) VALUES (1, 1);
INSERT INTO livro_assunto (livro_id, assunto_id) VALUES (2, 4);
INSERT INTO livro_assunto (livro_id, assunto_id) VALUES (3, 2);
INSERT INTO livro_assunto (livro_id, assunto_id) VALUES (4, 3);
INSERT INTO livro_assunto (livro_id, assunto_id) VALUES (5, 5);

create view vm_livros_por_autor as
select
au.id as autor_id,
au.nome as nome_autor,
l.id as livro_id,
l.titulo as titulo,
l.editora as editora,
l.edicao as edicao,
l.ano_publicacao as ano_publicacao,
l.valor as valor,
listagg(distinct ass.descricao, ', ') within group (order by ass.descricao) as assuntos
from autor au join livro_autor la on la.autor_id = au.id
join livro l on l.id = la.livro_id
left join livro_assunto las on las.livro_id = l.id
left join assunto ass on ass.id = las.assunto_id
group by au.id, au.nome, l.id, l.titulo, l.editora, l.edicao, l.ano_publicacao, l.valor;
