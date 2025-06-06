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
            String bairro = buscarBairro(texto);
            String numero = buscarNumero(texto);
            Matcher matcher = pattern.matcher(texto);

            if(matcher.find()){
                try{
                    String logradouro = matcher.group();
                    Endereco endereco = new Endereco(logradouro,bairro, numero, texto, cep, null);
                    enderecoDAO.save(endereco);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
    public String buscarBairro(String texto){
        texto = texto.toUpperCase();
        Pattern bairroPattern = Pattern.compile("(BAIRRO\\s)");
        Matcher matcher = bairroPattern.matcher(texto);
        if(matcher.find()){
            return texto.substring(matcher.start());
        }
        else{
            return "SEM BAIRRO";
        }
    }
    public String buscarNumero(String texto){
        texto = texto.toUpperCase();
        Pattern snPattern = Pattern.compile("\\b(S/N|SN)\\b");
        Matcher snMatcher = snPattern.matcher(texto);
        if (snMatcher.find()) {
            return "S/N";
        }
        // Nessa linha a preocupação é achar algo que comece com: (Numero, n°, num, n),
        // Depois também é buscado o intervalo numérico em si
        Pattern numeroPattern = Pattern.compile("\\b(N[ÚU]MERO|N[°]|NUM)?\\s*(\\d{1,5})\\b");
        Matcher numeroMatcher = numeroPattern.matcher(texto);
        if (numeroMatcher.find()) {
            return numeroMatcher.group(2);
        }

        return "";
    }
}
