package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Curso;
import br.gov.sp.fatec.springbootapp.entity.Aluno;

import br.gov.sp.fatec.springbootapp.repository.AlunoRepository;
import br.gov.sp.fatec.springbootapp.repository.CursoRepository;

import br.gov.sp.fatec.springbootapp.service.MatriculaService;

// @Transactional serve para garantir que só será salvo no banco se toda a operação funcionar, qualquer falha ele não irá comitar
// @Rollback serve para voltar o banco ao estado inicial ao teste
@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    // @Autowired serve para poder usar a instância que o spring gerou na propriedade declarada
    // Ex. a instância CursoRepository será usado na propriedade cursoRepo
    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private MatriculaService matService;

    // @Test é para informar que a seguinte função é um teste
    @Test
    void testaNovoCurso() {
        Curso curso = new Curso();
        curso.setNome("ADS");
        cursoRepo.save(curso);
        assertNotNull(cursoRepo.findByNome("ADS"));
    }	

    @Test
    void testaServicoCriarCurso() {
        Curso curso = matService.criarCurso("ADS");
        assertNotNull(curso);
    }

    @Test
    void testaNovoAluno() {
        Curso curso = new Curso();
        curso.setNome("ADS");
        cursoRepo.save(curso);
        Aluno aluno = new Aluno();
        aluno.setNome("Eduardo");
        aluno.setMatricula("123341234123");
        aluno.setCurso(curso);
        alunoRepo.save(aluno);
        assertNotNull(alunoRepo.findByNome("Eduardo"));
    }	

    @Test
    void testaServicoCriarAluno() {
        Curso curso = matService.criarCurso("ADS");
        Aluno aluno = matService.criarAluno("Eduardo", "1231414123123", curso);
    }

	@Test
    void testaAlunoFindByNome() {
        Curso curso = matService.criarCurso("DSM");
        Aluno aluno = matService.criarAluno("Eduardo2", "319283891731", curso);
        assertNotNull(alunoRepo.findByNome("Eduardo2"));
    }

	@Test
    void testaAlunoFindByNomeAndMatricula() {
        Curso curso = matService.criarCurso("DSM");
        Aluno aluno = matService.criarAluno("Eduardo3", "9893719823712", curso);
        assertNotNull(alunoRepo.findByNomeAndMatricula("Eduardo3", "9893719823712"));
    }

    @Test
    void testaAlunoBuscaPorNomeCurso() {
        Curso curso = matService.criarCurso("DSM");
        Aluno aluno = matService.criarAluno("Eduardo3", "9893719823712", curso);
        assertNotNull(alunoRepo.buscaPorNomeCurso("DSM"));
    }

    @Test
    void testaAlunobuscaPorNomeAndCurso() {
        Curso curso = matService.criarCurso("DSM");
        Aluno aluno = matService.criarAluno("Eduardo3", "9893719823712", curso);
        assertNotNull(alunoRepo.buscaPorNomeAndCurso("Eduardo", curso.id));
    }
}