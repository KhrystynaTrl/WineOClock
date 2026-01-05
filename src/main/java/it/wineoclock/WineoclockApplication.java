package it.wineoclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class WineoclockApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineoclockApplication.class, args);
	}

}
