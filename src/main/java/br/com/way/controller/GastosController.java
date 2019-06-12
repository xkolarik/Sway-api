package br.com.way.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.way.domain.Gastos;
import br.com.way.service.GastosService;

@RestController
@RequestMapping(path ="/way")
public class GastosController {

	@Autowired
	private GastosService gastosService;
	
	@RequestMapping(value = "/compra", method = RequestMethod.POST ,consumes = "application/json", produces= "application/json")
	public ResponseEntity<Gastos> criarFatura(@RequestBody Gastos gastos) {
		gastosService.criarCompra(gastos);
		return new ResponseEntity<>(gastos, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/compras/{codigousuario}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Gastos>> getListaDeCompras(@PathVariable int codigousuario, @RequestParam(value = "data", required=false) String data) {
		List<Gastos> gastos = gastosService.listaDeCompras(codigousuario, data);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fatura/{codigousuario}/{id}", produces="application/json", method = RequestMethod.GET)
	public ResponseEntity<Gastos> getDetalheFatura(@PathVariable int codigousuario, @PathVariable ObjectId id) {
		Gastos gastos = gastosService.detalheFatura(codigousuario, id);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fatura", method = RequestMethod.PUT)
	public ResponseEntity<Gastos> alterarFatura(@RequestBody Gastos gastos) {
		gastosService.alterarFatura(gastos);
		return new ResponseEntity<>(gastos, HttpStatus.ACCEPTED);
	}
}
