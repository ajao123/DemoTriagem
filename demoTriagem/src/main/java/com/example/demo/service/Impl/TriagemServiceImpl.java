package com.example.demo.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Triagem;
import com.example.demo.repository.TriagemRepository;
import com.example.demo.service.TriagemService;

@Service
public class TriagemServiceImpl implements TriagemService{
	@Autowired
	private TriagemRepository triagemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public Triagem salvar(Triagem categoria) {
		return triagemRepository.save(categoria);
	}
	
	public void remove(Long codigo) {
		triagemRepository.deleteById(codigo);
	}
	
	public Triagem buscarTriagemPeloCodigo(Long codigo) {
		Optional<Triagem> triagemSalva = triagemRepository.findById(codigo);
		if(triagemSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return triagemSalva.get();
	}
	
	public List<Triagem> listarTriagens(){
		return triagemRepository.findAll();
	}
	
	public Triagem atualizarTriagem(Long codigo, Triagem triagem) {
		Optional<Triagem> triagemSalva = triagemRepository.findById(codigo);
		if(triagemSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(triagem, triagemSalva.get(), "codigo");
		return triagemRepository.save(triagemSalva.get());
	}	
	
	public void atualizarPropriedadeSintomas(Long codigo, List<String> sintomas) {
		Triagem triagemSalva = buscarTriagemPeloCodigo(codigo);
		triagemSalva.setSintomas(sintomas);
		triagemRepository.save(triagemSalva);
	}
	
	public void atualizarPropriedadeNome(Long codigo, String nome) {
		Triagem triagemSalva = buscarTriagemPeloCodigo(codigo);
		triagemSalva.setNome(nome);
		triagemRepository.save(triagemSalva);
	}
	
	public void atualizarPropriedadePontuacao(Long codigo, Integer pontuacao) {
		Triagem triagemSalva = buscarTriagemPeloCodigo(codigo);
		triagemSalva.setPontuacao(pontuacao);
		triagemRepository.save(triagemSalva);
	}
	
	public void atualizarPropriedadeDataTriagem(Long codigo, LocalDate data_triagem) {
		Triagem triagemSalva = buscarTriagemPeloCodigo(codigo);
		triagemSalva.setData_triagem(data_triagem);
		triagemRepository.save(triagemSalva);
	}
}
