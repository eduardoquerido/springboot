package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Produto;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.ProdutoRepository;
import br.gov.sp.fatec.springbootapp.service.SegurancaService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private SegurancaService segService;

    @Autowired
    private ProdutoRepository prodRepo;

	@Test
	void contextLoads() {
    }

    @Test
    void testaNovaAutorizacao() {
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO"); // Autorização de usuário que terá acesso ao produto
        autRepo.save(aut);
        assertNotNull(autRepo.findByNome("ROLE_USUARIO"));
    }	

	@Test
    void testaNovoUsuarioFindByNome() {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setSenha("SenhaF0rte");
        usuarioRepo.save(usuario);
        assertNotNull(usuarioRepo.findByNome("Usuario"));
    }

    @Test
    void testaNovoUsuarioBuscaPorNome() {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setSenha("SenhaF0rte");
        usuarioRepo.save(usuario);
        assertNotNull(usuarioRepo.buscaUsuarioPorNome("Usuario"));
    }

    @Test
    void testaInsercaoUsuarioAutorizacao() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setSenha("senhamuitoforte");
        usuarioRepo.save(usuario);
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO");
        aut.setUsuarios(new HashSet<Usuario>());
        aut.getUsuarios().add(usuario);
        autRepo.save(aut);
        assertNotNull(aut.getUsuarios().iterator().next().getId());
    }

    @Test
    void testaNovoProdutoBuscaPorNome() {
        Produto prod = new Produto();
        prod.setNome("Produto1");
        prodRepo.save(prod);
        assertNotNull(prodRepo.buscaProdutoPorNome("Produto1"));
    }

    @Test
    void testaNovoProdutoFindByNome() {
        Produto prod = new Produto();
        prod.setNome("Produto1");
        prodRepo.save(prod);
        assertNotNull(prodRepo.findByNome("Produto1"));
    }

    @Test
    void testaInsercaoProdutoAutorizacao() {
        Produto prod = new Produto();
        prod.setNome("Produto Milagroso");
        prodRepo.save(prod);
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO"); // Autorização de usuário que terá acesso ao produto
        aut.setProdutos(new HashSet<Produto>());
        aut.getProdutos().add(prod);
        autRepo.save(aut);
        assertNotNull(aut.getProdutos().iterator().next().getId());
    }

    @Test
    void testaServicoCriaUsuario() {
        Usuario usuario = segService.criarUsuario("Usuário1", "senhamuitoforte", "ROLE_USUARIO");
        assertNotNull(usuario);
    }

    @Test
    void testaServicoCriarProduto() {
        Produto prod = segService.criarProduto("Produto1", "ROLE_USUARIO");
        assertNotNull(prod);
    }
}