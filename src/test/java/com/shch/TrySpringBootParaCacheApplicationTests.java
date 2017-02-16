package com.shch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan("com.shch.paracache")
public class TrySpringBootParaCacheApplicationTests {
	@Autowired
	private ParaCacheDemo demo;

	@Test
	public void contextLoads() {
		
		demo.printInfo();
	}

}
