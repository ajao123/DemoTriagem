package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Triagem;
import com.example.demo.repository.TriagemRepository;
import com.example.demo.service.TriagemService;
import com.example.demo.service.Impl.TriagemServiceImpl;

@RestController
@RequestMapping("/triagens")
public class TriagemController {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private TriagemService triagemService;
	
	@GetMapping
	public List<Triagem> listar(){
		return triagemService.listarTriagens();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Triagem> criar(@Valid @RequestBody Triagem triagem, HttpServletResponse response) {
		Triagem triagemSalva = triagemService.salvar(triagem);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, triagemSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(triagemSalva);
	}
	
	//Buscando um valor no banco de dados
	@GetMapping("/{codigo}")
	public ResponseEntity<Triagem> buscarPeloCodigo(@PathVariable Long codigo) {
		try {
			return ResponseEntity.ok(triagemService.buscarTriagemPeloCodigo(codigo));
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		triagemService.remove(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Triagem> atualizar(@PathVariable Long codigo, @Valid @RequestBody Triagem triagem){
		try {
			Triagem triagemSalva = triagemService.atualizarTriagem(codigo, triagem);
			return ResponseEntity.ok(triagemSalva);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{codigo}/sintomas")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeSintomas(@PathVariable Long codigo, @RequestBody List<String> sintomas) {
		 	triagemService.atualizarPropriedadeSintomas(codigo, sintomas);
	}
	
	@PutMapping("/{codigo}/nome")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeNome(@PathVariable Long codigo, @RequestBody String nome) {
		triagemService.atualizarPropriedadeNome(codigo, nome);
	}
	
	@PutMapping("/{codigo}/pontuacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadePontuacao(@PathVariable Long codigo, @RequestBody Integer pontuacao) {
		triagemService.atualizarPropriedadePontuacao(codigo, pontuacao);
	}
	
	@PutMapping("/{codigo}/data_triagem")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeDataTriagem(@PathVariable Long codigo, @RequestBody LocalDate data_triagem) {
		triagemService.atualizarPropriedadeDataTriagem(codigo, data_triagem);
	}
	
	
}
