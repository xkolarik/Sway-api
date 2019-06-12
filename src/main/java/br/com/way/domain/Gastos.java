package br.com.way.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

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
}
