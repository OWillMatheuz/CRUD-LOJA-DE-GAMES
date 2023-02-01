package org.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojagames.model.CategoriaGames;
import org.generation.lojagames.repository.CategoriaRepository;
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
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaGames>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaGames> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/computador/{computador}")
	public ResponseEntity<List<CategoriaGames>> 
	getByComputador(@PathVariable String computador){
		return ResponseEntity.ok(categoriaRepository
				.findAllByComputadorContainingIgnoreCase(computador));	
	}
	@GetMapping("/videogame/{videogame}")
	public ResponseEntity<List<CategoriaGames>> 
	getByVideogame(@PathVariable String videogame){
		return ResponseEntity.ok(categoriaRepository
				.findAllByVideogameContainingIgnoreCase(videogame));
	}
	@PostMapping
    public ResponseEntity<CategoriaGames> post(@Valid @RequestBody CategoriaGames categoriaGames){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoriaGames));
	}
	@PutMapping
    public ResponseEntity<CategoriaGames> put(@Valid @RequestBody CategoriaGames categoriaGames){
        return categoriaRepository.findById(categoriaGames.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(categoriaRepository.save(categoriaGames)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<CategoriaGames> categoriaGames = categoriaRepository.findById(id);
        
        if(categoriaGames.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        categoriaRepository.deleteById(id);   
	}
}
