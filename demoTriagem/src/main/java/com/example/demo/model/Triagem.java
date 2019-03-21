package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Utils.StringListConverter;

@Entity
@Table(name="triagem")
public class Triagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;	
	private String nome;
	private Integer pontuacao;
	private LocalDate data_triagem;
	
	@Convert(converter = StringListConverter.class)
	private List<String> sintomas;
	
	public Triagem() {
		this.data_triagem = LocalDate.now();
	}

	public Triagem(String nome, Integer pontuacao, LocalDate data_triagem, List<String> sintomas) {
		this.nome = nome;
		this.pontuacao = pontuacao;
		this.data_triagem = data_triagem;
		this.sintomas = sintomas;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNomePaciente(String nome) {
		this.nome = nome;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public LocalDate getData_triagem() {
		return data_triagem;
	}

	public void setData_triagem(LocalDate data_triagem) {
		this.data_triagem = data_triagem;
	}

	public List<String> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<String> sintomas) {
		this.sintomas = sintomas;
	}

	
	
}
