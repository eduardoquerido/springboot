package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    public List<Produto> findByNomeContainsIgnoreCase(String nome);

    public Produto findByNome(String nome);

    @Query("select u from Produto u where u.nome = ?1")
    public Produto buscaProdutoPorNome(String nome);
    
}