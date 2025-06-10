package com.example.demo;

import com.example.demo.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DashboardApplication.class, args);
		/*
			run.getBean(CsvService.class).lerPorCabecario(false);
			run.getBean(EmpreendimentoService.class).parseToRelational();
		 */
		
		run.getBean(EmpreendimentoService.class).buscarFinanciamentosPorRegiao();
		run.getBean(EmpreendimentoService.class).buscarEmpreendimentoPorModalidade();
		run.getBean(EmpreendimentoService.class).buscarEmpreendimentoPorSituacao();
		run.getBean(EnderecoService.class).buscarEnderecos();
	}
}