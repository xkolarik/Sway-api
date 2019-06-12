package br.com.way.service;

import java.util.List;

import br.com.way.domain.Categorias;

public interface CategoriasService {

	List<Categorias> listaCategoriasSugeridas(String categoria);
	
	List<Categorias> listaCategorias();
	
}
