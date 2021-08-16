package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Curso;
import br.gov.sp.fatec.springbootapp.entity.Aluno;

public interface MatriculaService {

    public Curso criarCurso(String nome);

    public Aluno criarAluno(String nome, String matricula, Curso curso);
    
}