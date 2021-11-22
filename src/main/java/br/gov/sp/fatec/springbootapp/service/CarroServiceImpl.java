package br.gov.sp.fatec.springbootapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Carro;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.CarroRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("carroService")
public class CarroServiceImpl implements CarroService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private CarroRepository carroRepo;

	@Override
	@Transactional
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public Carro adicionarCarro(String nome, String placa, String dono) {
		Usuario usuario = usuarioRepo.findByNome(dono);
		if(usuario == null) {
			throw new UsernameNotFoundException(
				"Usuário com nome " +
				dono +
				" não foi encontrado");
		}
		Carro carro = new Carro();
		carro.setNome(nome);
		carro.setPlaca(placa);
		carro.setDono(usuario);
		carroRepo.save(carro);
		
		return carro;
	}

}
