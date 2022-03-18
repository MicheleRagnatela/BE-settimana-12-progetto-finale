package it.epicode.be.epicenergyservice;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;


import it.epicode.be.epicenergyservice.model.StatoFattura;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	StatoFattura statoFattura;
	
	

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void getAllClienti() throws Exception {

		this.mockMvc.perform(get("/api/findallclienti")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void listaComuniWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/findallcomuni")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaClientiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/findallclienti")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void saveFatturaWhenUtenteMockIsAuthenticatedByUser() throws Exception {
		this.mockMvc.perform(delete("/api/deletecomune/1")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void deleteStatoWhenUtenteMockIsAuthenticatedByUser() throws Exception {
		this.mockMvc.perform(delete("/api/deletestato/3")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void addStatoFattura() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(statoFattura);
		MvcResult result = mockMvc
				.perform(post("/api/statofattura").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		JSONObject json_obj = new JSONObject(result.getResponse().getContentAsString());
		assertTrue(json_obj.has("nome"));
		

		
		assertTrue(json_obj.getString("nome").contains("PAGATA"));
	}

	@BeforeEach
	public void initContext() {

		statoFattura = new StatoFattura();
		statoFattura.setNome("PAGATA");

	}
	
	

	
}