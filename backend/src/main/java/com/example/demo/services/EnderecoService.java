package com.example.demo.services;

import com.example.demo.dao.EnderecoDAO;
import com.example.demo.model.Endereco;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EnderecoService extends ParseService {
    private EnderecoDAO enderecoDAO;
    private List<Pattern> patterns;
    public EnderecoService(CsvService csvService, EnderecoDAO enderecoDAO){
        super(csvService);
        this.enderecoDAO = enderecoDAO;
    }
    @Override
    public void parseToRelational() {
        this.patterns = List.of(
                Pattern.compile("^(RUA|R)\\s"),
                Pattern.compile("^(AV|AVENIDA)\\s"),
                Pattern.compile("^(TRAVESSA|TRAV)\\s"),
                Pattern.compile("^(LOTEAMENTO|LOTE)\\s"),
                Pattern.compile("^(SITIO)\\s"),
                Pattern.compile("^(RODOVIA)\\s"),
                Pattern.compile("(FAZENDA)")
        );
        for(Pattern pattern : patterns){
            buscarEnderecoPorRegex(pattern);
        }
    }

    public void buscarEnderecoPorRegex(Pattern pattern){

        for(CSVRecord csvRecord : csvService.getRecords()){
            String texto  = csvRecord.get("txt_endereco").toUpperCase();
            String cep = csvRecord.get("txt_cep");
            Matcher matcher = pattern.matcher(texto);

            if(matcher.find()){
                try{
                    String logradouro = matcher.group();
                    Endereco endereco = new Endereco(logradouro,"", "",texto, cep, null);
                    enderecoDAO.save(endereco);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
