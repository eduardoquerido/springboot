package br.gov.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.entity.Carro;
import br.gov.sp.fatec.springbootapp.repository.CarroRepository;
import br.gov.sp.fatec.springbootapp.service.CarroService;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/carros")
@CrossOrigin
public class CarroController {

    @Autowired
    private CarroService carroService;
    
    @Autowired
    private CarroRepository carroRepo;
    
    @PostMapping(value = "/novo")
    @JsonView(View.CarroCompleto.class)
    public Carro cadastrarCarro(@RequestBody CarroDTO carro) {
        return carroService.adicionarCarro(carro.getNome(), 
                carro.getPlaca(),
                carro.getUsuario());
    }
    
    @GetMapping(value = "/busca/{dono}")
    @JsonView(View.CarroCompleto.class)
    public List<Carro> buscarPorDono(
    		@PathVariable("dono") String dono) {
    	return carroRepo.findDonoByNome(dono);
    }

    
}

