package com.example.demo;
import com.example.demo.model.Construtora;
import com.example.demo.model.DadosUH;
import com.example.demo.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DashboardApplication.class, args);
		run.getBean(DadosUHService.class).parseToRelational();
		run.getBean(MunicipioService.class).parseToRelational();
		run.getBean(ConstrutoraService.class).parseToRelational();
		run.getBean(EnderecoService.class).parseToRelational();
		run.getBean(EmpreendimentoService.class).parseToRelational();

	}
}