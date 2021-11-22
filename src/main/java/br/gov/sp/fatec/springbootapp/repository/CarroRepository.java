package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootapp.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

    @PreAuthorize("isAuthenticated()")
    @Query("select c from Carro c inner join c.dono d where d.nome = ?1")
    public List<Carro> findDonoByNome(String dono);

}
