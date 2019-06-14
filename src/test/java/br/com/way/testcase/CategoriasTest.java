package br.com.way.testcase;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.way.domain.Categorias;
import br.com.way.service.CategoriasService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriasTest {

	@Autowired
	private CategoriasService categoriasService;
	List<Categorias> listCategorias = null;

	@Test
	public void listaCategoriasSugeridasTest() {

		Categorias expected = creatMock();

		Categorias actual = categoriasService.listaCategoriasSugeridas("Compra Outback").get(0);
		Assert.assertNotNull(actual);

		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void listaCategoriasTest() {

		Categorias expected = creatMock();

		List<Categorias> actual = categoriasService.listaCategorias();
		Assert.assertNotNull(actual);

		Assert.assertEquals(expected, actual.get(4));
	}

	public Categorias creatMock() {
		Categorias categoria = new Categorias();

		categoria.setId(new ObjectId("5d02db886d22b0be4c7d31d3"));
		categoria.setCategoria("Compra Outback");

		return categoria;
	}
}
