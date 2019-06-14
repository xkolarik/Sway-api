package br.com.way.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.way.SsantanderWayApplicationTest;
import br.com.way.controller.CategoriasController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriasControllerTests extends SsantanderWayApplicationTest{
	
	private MockMvc mockMvc;

	@Autowired
	private CategoriasController categoriasController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(categoriasController).build();
	}
	
	@Test
	public void testGETCategoriasController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/way/categorias/compra"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGETTodasCategoriasController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/way/categorias"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
