package com.example.myapp;

import org.springframework.boot.BootApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationStarter {
	public static void launchApp(String... arguments) {
		BootApplicationRunner.startApplication(ApplicationStarter.class, arguments);
	}

	public static void main(String[] parameters) {
		launchApp(parameters);
	}
}