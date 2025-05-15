package com.example.myapp;

//import org.springframework.boot.BootApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ApplicationStarter {
	public static void launchApp(String... arguments) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationStarter.class, arguments);
	}

	public static void main(String[] parameters) {
		launchApp(parameters);
	}
}