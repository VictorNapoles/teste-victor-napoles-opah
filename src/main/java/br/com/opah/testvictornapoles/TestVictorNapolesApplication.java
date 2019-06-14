package br.com.opah.testvictornapoles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestVictorNapolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestVictorNapolesApplication.class, args);
	}

}
