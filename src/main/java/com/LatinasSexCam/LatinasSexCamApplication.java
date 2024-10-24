package com.LatinasSexCam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class LatinasSexCamApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LatinasSexCamApplication.class);
		String port = System.getenv("PORT");
		if (port != null){
			app.setDefaultProperties(Collections.singletonMap("server.port",port));
		}
		app.run(args);
/*		SpringApplication.run(LatinasSexCamApplication.class, args);*/
	}

}
