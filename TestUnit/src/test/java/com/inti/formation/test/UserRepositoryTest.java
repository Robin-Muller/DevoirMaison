package com.inti.formation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.TestUnitApplication;
import com.inti.formation.Repository.UserRepository;
import com.inti.formation.entities.User;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestUnitApplication.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userrepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);
	
	@Test
	public void givenEntityRepository_whenSaving() {
		
		LOGGER.info("----Testing-----");
		System.out.println(userrepo);
		User addedUser = userrepo.save(new User(10,"dalii"));
		LOGGER.info("----Utilisateur saved-----");
		LOGGER.info("----Searching for Utilisateur-----");
		User foundUser = userrepo.findById(addedUser.getId());
		LOGGER.info("----Utilisateur Found-----");
		LOGGER.info("----Verifying Utilisateur-----");
		assertNotNull(foundUser);
		assertEquals(addedUser.getUserName(), foundUser.getUserName());
		LOGGER.info("----Utilisateur Verified-----");
		
	}
	
}
