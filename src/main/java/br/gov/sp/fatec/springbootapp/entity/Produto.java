package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "prd_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id")
    private Long id;

    @Column(name = "prd_nome")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "produto_autorizacao",
        joinColumns = { @JoinColumn(name = "prd_id")},
        inverseJoinColumns = { @JoinColumn(name = "aut_id") }
        )
    private Set<Autorizacao> prod_autorizacoes;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Autorizacao> getAutorizacoes() {
        return this.prod_autorizacoes;
    }

    public void setAutorizacoes(Set<Autorizacao> prod_autorizacoes) {
        this.prod_autorizacoes = prod_autorizacoes;
    }

}