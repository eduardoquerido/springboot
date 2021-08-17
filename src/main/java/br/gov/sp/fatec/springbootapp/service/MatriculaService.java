package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Curso;
import br.gov.sp.fatec.springbootapp.entity.Aluno;

public interface MatriculaService {

    public Aluno criarAluno(String nome, String matricula, String curso);
    
}