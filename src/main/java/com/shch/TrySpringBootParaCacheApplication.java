package com.shch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TrySpringBootParaCacheApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(TrySpringBootParaCacheApplication.class, args);
		ParaCacheDemo demo=(ParaCacheDemo) ctx.getBean("demo");
		demo.printInfo();
	}
}
