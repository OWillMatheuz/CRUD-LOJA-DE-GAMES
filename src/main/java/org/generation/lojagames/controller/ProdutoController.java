package org.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojagames.model.ProdutoGames;
import org.generation.lojagames.repository.CategoriaRepository;
import org.generation.lojagames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoGames>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoGames> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/consoleJogos/{consoleJogos}")
	public ResponseEntity<List<ProdutoGames>> getByConsoleJogos(@PathVariable String consoleJogos){
		return ResponseEntity.ok(produtoRepository
				.findAllByConsoleJogosContainingIgnoreCase(consoleJogos));
	}
	@PostMapping
	public ResponseEntity<ProdutoGames> post(@Valid @RequestBody ProdutoGames produtoGames){
		if (categoriaRepository.existsById(produtoGames.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(produtoRepository.save(produtoGames));
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PutMapping
	public ResponseEntity<ProdutoGames> put(@Valid @RequestBody ProdutoGames produtoGames){
		if (produtoRepository.existsById(produtoGames.getId())){
			
			if (categoriaRepository.existsById(produtoGames.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produtoGames));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<ProdutoGames> produtoGames = produtoRepository.findById(id);
		
		if(produtoGames.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
		
	}
}
