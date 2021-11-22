package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity
@Table(name="car_carro")
public class Carro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "car_id")
	@JsonView(View.CarroCompleto.class)
	private Long id;
	
	@Column(name = "car_nome", unique=true, length = 100, nullable = false)
	@JsonView(View.CarroCompleto.class)
	private String nome;
	
	@Column(name = "car_placa", length = 10, nullable = false)
	@JsonView(View.CarroCompleto.class)
	private String placa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_owner_id")
	@JsonView(View.CarroResumo.class)
	private Usuario dono;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}
	
}
