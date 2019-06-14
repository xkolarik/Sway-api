package br.com.way.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.way.domain.Categorias;
import br.com.way.domain.Gastos;
import br.com.way.repository.CategoriasRepository;
import br.com.way.repository.GastosRepository;

@Service
public class GastosServiceImpl implements GastosService {

	private GastosRepository gastosRepository;
	private CategoriasRepository categeoriasRepository;

	@Autowired
	public GastosServiceImpl(GastosRepository gastosRepository, CategoriasRepository categoriasRepository) {
		this.gastosRepository = gastosRepository;
		this.categeoriasRepository = categoriasRepository;
	}

	@Override
	public List<Gastos> listaDeCompras(int codigousuario, String data) {
		List<Gastos> gastos = new ArrayList<>();
		if (data == null) {
			gastos = gastosRepository.findAllByCodigoUsuario(codigousuario, new Sort(Direction.DESC, "data"));
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			
			LocalDateTime dataInicio = LocalDateTime.parse(data, formatter);
			LocalDateTime dataFim = dataInicio.plusDays(1);
			
			gastos = gastosRepository.findAllByCodigoUsuarioData(codigousuario, dataInicio, dataFim,
					new Sort(Direction.DESC, "data"));
		}
		return gastos;
	}

	@Override
	public Gastos detalheFatura(int codigousuario, ObjectId id) {
		Gastos gastos = new Gastos();
		gastos = gastosRepository.findByIdAndCodigoUsuario(id, codigousuario);
		return gastos;
	}

	@Override
	public Gastos criarCompra(Gastos gastos) {
		gastos.setId(ObjectId.get());
		return validaGasto(gastos);
	}

	@Override
	public Gastos alterarFatura(Gastos gastos) {
		return validaGasto(gastos);
	}

	public Gastos validaGasto(Gastos gastos) {
		Categorias categoria = new Categorias();
		categoria = getCategoria(gastos);
		if (categoria != null && categoria.getCategoria().equals(gastos.getDescricao())) {
			gastos.setIdcategoria(new ObjectId(categoria.getId()));
		} else {
			if (gastos.getDescricao() != null) {
				categoria = new Categorias();
				ObjectId idCategoria = ObjectId.get();
				categoria.setId(idCategoria);
				categoria.setCategoria(gastos.getDescricao());
				categeoriasRepository.save(categoria);
				gastos.setIdcategoria(idCategoria);
			}
		}
		return gastosRepository.save(gastos);
	}

	public Categorias getCategoria(Gastos gastos) {
		return categeoriasRepository.findByNomeCategoria(gastos.getDescricao());
	}
}
