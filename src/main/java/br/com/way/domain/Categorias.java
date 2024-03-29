package br.com.way.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Não usar Lombok com spring 2.1.5
//@Data

@Document
public class Categorias {
	
	@JsonIgnore
	@Id
	private ObjectId _id;
	@Indexed
	private String categoria;
	
	public Categorias() {}
	
	public Categorias(ObjectId _id,  String categoria) {
		this._id = _id;
		this.categoria = categoria;
	}
	
	public String getId() {
		return _id.toHexString();
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
