package org.generation.lojagames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtogames")
public class ProdutoGames {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Console de Jogos é obrigatório !")
	@Size(min = 5, max = 100, message = "O atributo Console de Jogos deve conter no mínimo 5 e no máximo 100 caracteres")
	private String consoleJogos;
	
	@NotBlank(message = "O atributo Jogos em Midia é obrigatório !")
	@Size(min = 5, max = 100, message = "O atributo Jogos em Midia deve conter no mínimo 5 e no máximo 100 caracteres")
	private String jogosmidia;
	
	@NotBlank(message = "O atributo Acessórios é obrigatório !")
	@Size(min = 5, max = 100, message = "O atributo Acessórios deve conter no mínimo 5 e no máximo 100 caracteres")
	private String acessorios;
	
	@NotNull
	public BigDecimal quantidade;
	
	@NotNull
	public BigDecimal valorConsole;
	
	@NotNull
	public BigDecimal valorMidia;
	
	@NotNull
	public BigDecimal valorAcessorio;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private CategoriaGames categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private UsuarioModel usuario;
	
	
	
	
	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public CategoriaGames getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaGames categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConsoleJogos() {
		return consoleJogos;
	}

	public void setConsoleJogos(String consoleJogos) {
		this.consoleJogos = consoleJogos;
	}

	public String getJogosmidia() {
		return jogosmidia;
	}

	public void setJogosmidia(String jogosmidia) {
		this.jogosmidia = jogosmidia;
	}

	public String getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(String acessorios) {
		this.acessorios = acessorios;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorConsole() {
		return valorConsole;
	}

	public void setValorConsole(BigDecimal valorConsole) {
		this.valorConsole = valorConsole;
	}

	public BigDecimal getValorMidia() {
		return valorMidia;
	}

	public void setValorMidia(BigDecimal valorMidia) {
		this.valorMidia = valorMidia;
	}

	public BigDecimal getValorAcessorio() {
		return valorAcessorio;
	}

	public void setValorAcessorio(BigDecimal valorAcessorio) {
		this.valorAcessorio = valorAcessorio;
	}

}
