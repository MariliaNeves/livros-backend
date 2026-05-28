





create table autor (
    id integer generated always as identity primary key,
    nome varchar(40) not null
);

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
