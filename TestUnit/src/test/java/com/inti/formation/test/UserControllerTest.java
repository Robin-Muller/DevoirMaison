package com.inti.formation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inti.formation.TestUnitApplication;
import com.inti.formation.controllers.UserController;
import com.inti.formation.entities.User;
import com.inti.formation.metier.UserMetier;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestUnitApplication.class)
public class UserControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	protected MockMvc mvc;

	protected String uri;
	@InjectMocks
	private UserController usercontroller;
	
	@Mock
	private UserMetier userMetierToMock;

	
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
	}

	@Autowired
	private UserMetier usermetier;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

	public UserControllerTest() {
		super();
		this.uri = "/users";
	}

	@Test
	public void getAllEntityList() {

		MvcResult mvcResult;
		try {
			LOGGER.info("----Testing getAllEntity Method-----");
			LOGGER.info("----Constructing Utilisateur-----");
			LOGGER.info("----Saving Utilisateur-----");
			usermetier.addUser(new User(2, "dalii"));
			LOGGER.info("----Mocking Context Webservice-----");
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/all").accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			LOGGER.info("----Getting Http Stratus-----");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("----Verifying HTPP Status-----");
			assertEquals(200, status);
			LOGGER.info("----Getting HTTP Response-----");
			String content = mvcResult.getResponse().getContentAsString();
			LOGGER.info("----Deserializing JSON Response-----");
			User[] userList = this.mapFromJson(content, User[].class);
			assertTrue(userList.length > 0);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void createEntity() {

		LOGGER.info("----Testing createEntity Method-----");
		LOGGER.info("----Constructing Utilisateur-----");
		User user = new User(50, "sala7");
		MvcResult mvcResult;
		try {
			LOGGER.info("----Serializing Utilisateur Object-----");
			String inputJson = this.mapToJson(user);
			LOGGER.info("----Mocking ContextWebservice and invoking the webservice-----");

			mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri + "/all")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			LOGGER.info("----Getting Http Status-----");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("----Verifying HTPP Status-----");
			assertEquals(200, status);
			LOGGER.info("----Searching for Utilisateur-----");
			User userfound = usermetier.getUserById(new Long(50));
			LOGGER.info("---- Verifying Utilisateur-----");
			assertEquals(userfound.getUserName(), user.getUserName());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void updateEntity() {
		try {
			LOGGER.info("------------------ Testing updateEntity Method ------------------");
			LOGGER.info("------------------ Constructing Utilisateur ------------------");
			User oldUser = new User(2, "Lemon");
			LOGGER.info("------------------ Savinf Utilisateur ------------------");
			usermetier.addUser(oldUser);
			LOGGER.info("------------------ Modifying Utilisateur ------------------");
			User newUser = new User(2, "Lemonade");
			LOGGER.info("------------------ Serializing Utilisateur Object ------------------");
			String inputJson = this.mapToJson(newUser);
			LOGGER.info("------------------ Mocking Context Webservice and invoking the webservice ------------------");
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/2")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			LOGGER.info("------------------ Getting HTTP Status ------------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("------------------ Verifying HTTP Status ------------------");
			assertEquals(200, status);
			LOGGER.info("------------------ Searching for Utilisateur ------------------");
			User userFound = usermetier.getUserById(new Long(2));
			LOGGER.info("------------------ Verifying Utilisateur ------------------");
			assertEquals(userFound.getUserName(), newUser.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteEntity() {
		LOGGER.info("------------------ Testing deleteEntity Method ------------------");
		try {
			LOGGER.info("------------------ Constructing Utilisateur ------------------");
			LOGGER.info("------------------ Savinf Utilisateur ------------------");
			usermetier.addUser(new User(2, "Lemon"));
			LOGGER.info("------------------ Mocking Context Webservice and invoking the webservice ------------------");
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/2")).andReturn();
			LOGGER.info("------------------ Getting HTTP Status ------------------");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("------------------ Verifying HTTP Status ------------------");
			assertEquals(200, status);
			LOGGER.info("------------------ Searching for Utilisateur ------------------");
			User userFound = usermetier.getUserById(new Long(2));
			LOGGER.info("------------------ Verifying Utilisateur ------------------");
			assertEquals(userFound, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void exercice() {
        LOGGER.info("------------------ Executing Exercice------------------");
        User user = new User();
        usercontroller.addNewUser(user);
        verify(userMetierToMock).addUser(user);
        }
	
	
	@Test
	public void verif_status() {

		LOGGER.info("----Testing verif_status-----");
		LOGGER.info("----Constructing Utilisateur-----");
		User user = new User(50, "sala7");
		MvcResult mvcResult;
		try {
			LOGGER.info("----Serializing Utilisateur Object-----");
			String inputJson = this.mapToJson(user);
			LOGGER.info("----Mocking ContextWebservice and invoking the webservice-----");

			mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri + "/all")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			LOGGER.info("----Getting Http Status-----");
			int status = mvcResult.getResponse().getStatus();
			LOGGER.info("----Verifying HTPP Status-----");
			assertEquals(200, status);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * Serialize the given object into Json
	 *
	 * @param obj
	 * @return String
	 * @throws JsonProcessingException
	 */
	protected final String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * Deserialize a given Json string into an object
	 *
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	protected final <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
