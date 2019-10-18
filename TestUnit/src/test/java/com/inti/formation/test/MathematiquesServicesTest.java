package com.inti.formation.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inti.formation.entities.MathematiquesServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathematiquesServicesTest {
	
	@Test
	public void addition() {
		
		MathematiquesServices mathServ = new MathematiquesServices();
		long a = 5;
		long b = 6;
		long result = mathServ.addition(a,  b);
		assertEquals(result, 11);
	}

}
