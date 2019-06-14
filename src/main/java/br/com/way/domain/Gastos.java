package br.com.way.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//Não usar Lombok com spring 2.1.5

@Data
@Document
public class Gastos {
	
	@JsonIgnore
	@Id
	private ObjectId _id;
	@JsonIgnore
	private ObjectId idcategoria;
 	private String descricao;
	private BigDecimal valor;
	private int codigousuario;
	
	@Indexed
	private LocalDateTime data;
	
	public String getId() {
		return _id.toHexString();
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public ObjectId getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(ObjectId idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getCodigousuario() {
		return codigousuario;
	}
	public void setCodigousuario(int codigousuario) {
		this.codigousuario = codigousuario;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}