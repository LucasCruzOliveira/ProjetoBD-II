package com.example.demo.services;

import com.example.demo.dao.MunicipioDAO;
import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Endereco;
import com.example.demo.model.Municipio;
import lombok.Getter;
import lombok.Setter;
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

@Service
@Getter
@Setter
public class CsvService {
    private final CSVParser parser;
    private final List<CSVRecord> records;

    public CsvService(MunicipioDAO municipioDAO) {
        this.parser = criarParser("./src/main/resources/base_ogu.csv", ';');
        this.records = parser.getRecords();
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
