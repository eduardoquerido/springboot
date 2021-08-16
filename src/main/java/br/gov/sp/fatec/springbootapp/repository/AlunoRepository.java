package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    public Aluno findByNome(String nome);

    public Aluno findByNomeAndMatricula(String nome, String matricula);

    @Query("select a from Aluno a where a.curso.nome = ?1")
    public List<Aluno> buscaPorNomeCurso(String curso);

    @Query("select a from Aluno a where a.nome = ?1 and a.curso.id = ?2")
    public List<Aluno> buscaPorNomeAndCurso(String nome, Long curso_id);
    
}

//     Exemplos:

//     @Query("select u from Usuario u inner join u.autorizacoes a where a.nome = ?1")
//     public List<Usuario> buscaPorNomeAutorizacao(String autorizacao);