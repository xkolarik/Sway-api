package br.com.way.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.way.SsantanderWayApplicationTest;
import br.com.way.controller.GastosController;
import br.com.way.domain.Gastos;

public class GastosControllerTests extends SsantanderWayApplicationTest {

	private MockMvc mockMvc;

	@Autowired
	private GastosController gastosController;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(gastosController).build();
	}

	@Test
	public void testGETGastosController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/way/fatura/9"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETGastosDetalheController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/way/fatura/9/5d00abad6d22b04d58c83589"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTGastoController() throws Exception {
		
		String json = "{\"descricao\":\"Compra Uber\",\"valor\":100,\"codigousuario\":9,\"data\":\"2019-06-12T04:37:00\"}";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/way/venda")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
		Gastos gasto = new Gastos();
		
		gasto.setId(new ObjectId("5d00abad6d22b04d58c83589"));
		gasto.setDescricao("Compra Uber");
		gasto.setValor(new BigDecimal(200));
		gasto.setCodigousuario(3);
		gasto.setData(null);
		gasto.setIdcategoria(new ObjectId("5d00ab366d22b0a4dc28325f"));
//		with().body(gasto).when().request("POST", "/way/vendas").then().statusCode(201);
		
	}

	@Test
	public void testPUTAddressController() throws Exception {
		String json = "{\"descricao\":\"Compra Uber\",\"valor\":50,\"codigousuario\":9,\"data\":\"2019-06-12T04:37:00\"}";
		
		Gastos gasto = new Gastos();
		
		gasto.setId(new ObjectId("5c5bcc6c0000000000000000"));
		gasto.setDescricao("Compra Uber");
		gasto.setValor(new BigDecimal(200));
		gasto.setCodigousuario(1);
		gasto.setData(LocalDateTime.now());
		gasto.setIdcategoria(new ObjectId("5c5bcc6c0000000000000001"));
		//gastosService.alterarGasto(gasto);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/way/fatura").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}
}
