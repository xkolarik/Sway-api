package br.com.way.testcase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.way.domain.Gastos;
import br.com.way.service.GastosService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GastosTest {

	@Autowired
	private GastosService gastosService;

	@Test
	public void creatCompraTest() {

		Gastos compraExpcted = creatMock(); // Poderia validar todos os campos do metodo. Estou apenas validando o metodo
		Gastos compra = gastosService.criarCompra(compraExpcted);
		Assert.assertSame(compraExpcted, compra);

	}

	@Test
	public void listaComprasTest() {

		Gastos compra = creatMock();
		gastosService.criarCompra(compra);

		Gastos listaGastosActual = gastosService.listaDeCompras(9, "14/06/2019 02:05").get(0);
		Assert.assertNotNull(listaGastosActual);
		Assert.assertEquals(compra, listaGastosActual);

		// Assert.assertEquals(compra, is(listaGastosExpected));
	}

	@Test
	public void detalheFaturaTest() {

		Gastos compraExpcted = creatMock();
		Gastos compra = gastosService.criarCompra(compraExpcted);
		Gastos detalheFatura = gastosService.detalheFatura(9, compra.get_id());

		Assert.assertEquals(compra, detalheFatura);
	}

	@Test
	public void alteraFaturaTest() {

		Gastos expected = new Gastos();

		expected.setDescricao("Compra Coco Bambu");
		expected.setValor(new BigDecimal(300));
		expected.setCodigousuario(9);
		expected.setData(LocalDateTime.now());

		Gastos fatura = gastosService.alterarFatura(expected);

		Assert.assertEquals(expected, fatura);

	}

	public Gastos creatMock() {

		Gastos compra = new Gastos();

		compra.setDescricao("Compra Outback");
		compra.setValor(new BigDecimal(200));
		compra.setCodigousuario(9);
		compra.setData(LocalDateTime.now());

		return compra;
	}
}
