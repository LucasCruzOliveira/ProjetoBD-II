package com.example.demo.services;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Getter
@Setter
public class CsvService {
    private  CSVParser parser;
    private  List<CSVRecord> records;

    public CsvService() {
        try{
            criarParser("base_ogu.csv", ';');
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void lerPorCabecario(boolean repeticao){
        List<String> cabecalhos = parser.getHeaderNames();

        for(String cabecalho : cabecalhos){
            Collection<String> lista = new HashSet<>();
            if(repeticao) {
                lista = new ArrayList<>();
            }
            for(CSVRecord csvRecord  : records) {
                String linha = csvRecord.get(cabecalho).toUpperCase().trim();
                lista.add(linha);
            }
            escrever(lista, cabecalho);
        }

    }


    public void escrever(Collection<String> linhas, String cabecalho){
        String path = "";
        if(linhas instanceof List<String>){
            path = "src/main/resources/dados-com-repeticao/";
        }else {
            path = "src/main/resources/dados-sem-repeticao/";
        }
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + cabecalho + ".csv"));
                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(cabecalho))
        ) {
            for (String linha : linhas){
                printer.printRecord(linha);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void criarParser(String path, char separador) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException("Arquivo não encontrado no classpath: " + path);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        this.parser = CSVFormat.DEFAULT
                .withDelimiter(separador)
                .withFirstRecordAsHeader()
                .parse(reader);
        this.records = parser.getRecords();
    }

}
