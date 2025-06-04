package com.example.demo;

import com.example.demo.servicos.CsvService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
		CsvService csvService = new CsvService();
		csvService.lerPorCabecario("src/main/resources/base_ogu.csv", true);
		csvService.lerPorCabecario("src/main/resources/base_ogu.csv", false);
	}
}
