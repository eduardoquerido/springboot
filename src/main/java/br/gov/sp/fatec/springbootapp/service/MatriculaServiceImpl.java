package br.gov.sp.fatec.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Aluno;
import br.gov.sp.fatec.springbootapp.entity.Curso;
import br.gov.sp.fatec.springbootapp.repository.AlunoRepository;
import br.gov.sp.fatec.springbootapp.repository.CursoRepository;

@Service("matriculaService")
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Transactional
    public Curso criarCurso(String nome) {
        Curso curso = new Curso();
        curso.setNome(nome);
        cursoRepo.save(curso);
        return curso;
    }
    
    @Transactional
    public Aluno criarAluno(String nome, String matricula, Curso curso) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setCurso(curso);
        alunoRepo.save(aluno);
        return aluno;
    }
}