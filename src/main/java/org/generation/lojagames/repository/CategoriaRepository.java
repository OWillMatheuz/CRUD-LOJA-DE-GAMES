package org.generation.lojagames.repository;

import java.util.List;

import org.generation.lojagames.model.CategoriaGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<CategoriaGames, Long> {
	public List<CategoriaGames> findAllByComputadorContainingIgnoreCase
	(@Param("computador") String computador);
	public List<CategoriaGames> findAllByVideogameContainingIgnoreCase
	(@Param("videogame") String videogame);
}
