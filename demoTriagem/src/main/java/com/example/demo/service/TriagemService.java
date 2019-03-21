package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Triagem;

public interface TriagemService {
	Triagem salvar(Triagem categoria);
	void remove(Long codigo);
	Triagem buscarTriagemPeloCodigo(Long codigo);
	List<Triagem> listarTriagens();
	Triagem atualizarTriagem(Long codigo, Triagem triagem);
	void atualizarPropriedadeSintomas(Long codigo, List<String> sintomas);
	void atualizarPropriedadeNome(Long codigo, String nome);
	void atualizarPropriedadePontuacao(Long codigo, Integer pontuacao);
	void atualizarPropriedadeDataTriagem(Long codigo, LocalDate data_triagem);	
	
}
