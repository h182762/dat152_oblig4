package no.hvl.dat152;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Oblig4WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oblig4WebAppApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8299/items";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/9991", String.class);
		System.out.println(response.toString());
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
