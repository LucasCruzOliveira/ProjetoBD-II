package com.example.demo;
import com.example.demo.model.Construtora;
import com.example.demo.services.ConstrutoraService;
import com.example.demo.services.EnderecoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DashboardApplication.class, args);
		run.getBean(ConstrutoraService.class).parseToRelational();
		run.getBean(EnderecoService.class).parseToRelational();

	}
}