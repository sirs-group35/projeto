package sirs.group35.ala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class AlaApplication {

	public static void main(String[] args) {
		System.out.println(SpringVersion.getVersion()+"\n\n\n\n\n\n\n");
		SpringApplication.run(AlaApplication.class, args);
	}

}
