package org.generation.lojagames.repository;

import java.util.List;

import org.generation.lojagames.model.ProdutoGames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoGames, Long>{
	public List<ProdutoGames> findAllByConsoleJogosContainingIgnoreCase
		(@Param("consoleJogos")String consoleJogos);
}
