package br.gov.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import br.gov.sp.fatec.springbootapp.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	@PreAuthorize("isAuthenticated()")
    public List<Carro> findDonoByNome(String nome);

}
