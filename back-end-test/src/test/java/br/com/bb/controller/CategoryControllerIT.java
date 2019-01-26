package br.com.bb.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.bb.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CategoryControllerIT {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listAll() throws Exception {
		mockMvc.perform(get("/category/listAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("Alimentos")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].name", is("Eletrodomésticos")))
				.andExpect(jsonPath("$[2].id", is(3))).andExpect(jsonPath("$[2].name", is("Móveis")));
	}

	@Test
	public void listByFilterToFindD() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=d")).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(2))).andExpect(jsonPath("name", is("Eletrodomésticos")));
	}

	@Test
	public void listByFilterToFindEUpper() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=E")).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(2))).andExpect(jsonPath("name", is("Eletrodomésticos")));
	}

	@Test
	public void listByFilterToFindELower() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=E")).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(2))).andExpect(jsonPath("name", is("Eletrodomésticos")));
	}

	@Test
	public void listByFilterToFindOhUpper() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=Ó")).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(3))).andExpect(jsonPath("name", is("Móveis")));
	}

	@Test
	public void listByFilterToFindOhLower() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=Ó")).andExpect(status().isOk())
				.andExpect(jsonPath("id", is(3))).andExpect(jsonPath("name", is("Móveis")));
	}

	@Test
	public void listByFilterNoContent() throws Exception {
		mockMvc.perform(get("/category/listByFilter?toFind=z")).andExpect(status().isNoContent());
	}

	@Test
	public void listByFilterPrecondition() throws Exception {
		mockMvc.perform(get("/category/listByFilter")).andExpect(status().isPreconditionFailed());
	}
}
