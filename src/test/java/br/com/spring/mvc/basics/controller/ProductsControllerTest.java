package br.com.spring.mvc.basics.controller;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amazonaws.services.s3.AmazonS3Client;

import br.com.spring.mvc.basics.config.AppWebConfig;
import br.com.spring.mvc.basics.config.DataSourceConfigurationTest;
import br.com.spring.mvc.basics.config.JPAConfiguration;
import br.com.spring.mvc.basics.config.SecurityConfiguration;
import br.com.spring.mvc.basics.daos.ProductDAO;
import br.com.spring.mvc.basics.infrastructure.AmazonS3FileSaver;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { 
		AppWebConfig.class, 
		JPAConfiguration.class, 
		AmazonS3Client.class,
		SecurityConfiguration.class,
		DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class ProductsControllerTest {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	@Transactional
	public void shouldListAllBooksInTheHome() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products/list.jsp"));

	}

}
