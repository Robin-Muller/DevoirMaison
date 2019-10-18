package com.inti.formation.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.TestUnitApplication;
import com.inti.formation.Repository.UserRepository;
import com.inti.formation.entities.User;
import com.inti.formation.metier.UserMetier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestUnitApplication.class)
public class UserMetierTest {
	

@InjectMocks
private UserMetier userMetiertoMock;
@Autowired
private UserMetier userMetier;
@Mock
private UserRepository userrepo;
private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

@Before
public void setUp() {
	
	MockitoAnnotations.initMocks(this);
}
	
	
@Test
public void givenUsers_getHalfOfNumber() {
	
	LOGGER.info("----Testing-----");
	LOGGER.info("----Constructing Utilisateurs-----");
	LOGGER.info("----Mocking getAll() method of IUtilisateurService-----");
Mockito.when(userMetiertoMock.getAllUsers()).thenReturn(Arrays.asList(new User(10, "dalii"), new User(10, "dalii"), new User(1, "dalii"), new User(10, "dalii"), new User(2, "dalii")));
LOGGER.info("----Method Mocked-----");
LOGGER.info("----Verifying results-----");
assertEquals(2,  userMetier.getUserNbrHalf(userMetiertoMock.getAllUsers()));

}

public void should_store_when_save_is_called() {
	LOGGER.info("----Executing should_store_when_save_is_called" + "is_called test Of UserServiceImplTest-----");
User myUser = new User();
userMetiertoMock.addUser(myUser);
Mockito.verify(userrepo).save(myUser);
		
}




}
