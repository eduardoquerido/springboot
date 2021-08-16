package br.gov.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootapp.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Curso findByNome(String nome);
    
}