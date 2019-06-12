package br.com.way.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.way.domain.Gastos;

public interface GastosService {

	List<Gastos> listaDeCompras(int codigousuario, String data);
	
	Gastos detalheFatura(int codigousuario, ObjectId id);
	
	Gastos criarCompra(Gastos gastos);
	
	Gastos alterarFatura(Gastos gastos);
	
}
