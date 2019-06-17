package br.com.opah.testvictornapoles;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class TestVictorNapolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestVictorNapolesApplication.class, args);
	}
	
	@Bean
	  public Executor asyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(5);
	    executor.setMaxPoolSize(7);
	    executor.setQueueCapacity(700);
	    executor.initialize();
	    return executor;
	  }

}
