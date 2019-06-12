package br.com.way.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.way.domain.Categorias;
import br.com.way.service.CategoriasService;

@RestController
@RequestMapping("/way")
public class CategoriasController {
	
	@Autowired
	private CategoriasService categoriasService;
	
	@RequestMapping(value = "/categorias/{categoria}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Categorias>> getCategoria(@PathVariable String categoria){
		List<Categorias> categorias = categoriasService.listaCategoriasSugeridas(categoria);
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categorias", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Categorias>> getCategorias(){
		List<Categorias> categorias = categoriasService.listaCategorias();
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
}
