package br.gov.sp.fatec.springbootapp.service;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Produto;

public interface SegurancaService {

    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public Produto criarProduto(String nome, String autorizacao);
    
}