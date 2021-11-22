package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Carro;

public interface CarroService {
	
	public Carro adicionarCarro(String nome, String placa, 
			String dono);

}
