package com.example.demo;
import com.example.demo.model.Construtora;
import com.example.demo.services.ConstrutoraService;
import com.example.demo.services.EnderecoService;
import com.example.demo.services.MunicipioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DashboardApplication.class, args);
		run.getBean(MunicipioService.class).parseToRelational();
		run.getBean(ConstrutoraService.class).parseToRelational();
		run.getBean(EnderecoService.class).parseToRelational();
	}
}