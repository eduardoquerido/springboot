-- drop database curso;
create schema curso;

use curso

-- drop user 'usuario'@'localhost';
-- flush privileges;
CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'senhaforte';

grant select, insert, delete, update on curso.* to 'usuario'@'localhost';

create table cur_curso (
    cur_id bigint unsigned not null auto_increment,
    cur_nome varchar(20) not null,
    primary key (cur_id),
    unique key uni_cur_nome (cur_nome)
);

create table alu_aluno (
    alu_id bigint unsigned not null auto_increment,
    alu_curso_id bigint unsigned not null,
    alu_nome varchar(20) not null,
    alu_matricula varchar(50) not null,
    primary key (alu_id),
    unique key uni_aluno_matricula (alu_matricula),
    foreign key alu_curso_fk (alu_curso_id) references cur_curso (cur_id) on delete restrict on update cascade
);