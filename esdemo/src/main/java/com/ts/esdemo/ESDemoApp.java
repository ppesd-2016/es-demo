package com.ts.esdemo;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESDemoApp {

	private static final Logger logger = LoggerFactory.getLogger(ESDemoApp.class);

	public static void main(String[] args) {
		logger.info("Starting ES Demo application with given args: " + Arrays.asList(args));
		SpringApplication.run(ESDemoApp.class, args);
	}
}
