package com.example.demo.servicos;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CsvService {


    public void lerPorCabecario(String path, boolean repeticao){

        CSVParser parser = criarParser("src/main/resources/base_ogu.csv", ';');
        List<CSVRecord> records = parser.getRecords();
        List<String> cabecalhos = parser.getHeaderNames();

        for(String cabecalho : cabecalhos){
            Collection<String> lista = new HashSet<>();
            if(repeticao) {
                lista = new ArrayList<>();
            }
            for(CSVRecord record  : records) {
                String linha = record.get(cabecalho).toUpperCase().trim();
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


    public CSVParser criarParser(String path, char separador){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser = CSVFormat.DEFAULT
                    .withDelimiter(separador)
                    .withFirstRecordAsHeader()
                    .parse(reader);
            return csvParser;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
