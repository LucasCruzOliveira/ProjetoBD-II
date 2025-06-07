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
        String parada= "[\\s.]+(.+?)(?=\\s*(,|-|Bairro\\b|$))";
        this.patterns = List.of(
                Pattern.compile("^(RUA|R)"+ parada, Pattern.CASE_INSENSITIVE),
                Pattern.compile("^(AV|AVENIDA)" + parada, Pattern.CASE_INSENSITIVE),
                Pattern.compile("^(TRAVESSA|TRAV)" + parada),
                Pattern.compile("^(LOTEAMENTO|LOTE)" + parada),
                Pattern.compile("^(SITIO)" + parada, Pattern.CASE_INSENSITIVE),
                Pattern.compile("^(RODOVIA)" + parada, Pattern.CASE_INSENSITIVE),
                Pattern.compile("(FAZENDA)" + parada, Pattern.CASE_INSENSITIVE)
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

            Matcher matcher = pattern.matcher(texto);

            if(matcher.find()){
                try{

                    String logradouro = matcher.group().replace("S/N", "")
                            .replaceAll("(\\b(SN|S/N|NR|N\\.|N°|N)\\b.*)", "");
                    String numero = buscarNumero(texto, matcher.end());
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
    public String buscarNumero(String texto, int inicioBusca){
        texto = texto.toUpperCase();

        // procura S/N ou SN primeiro
        Pattern snPattern = Pattern.compile("\\b(S/N|SN)\\b");
        Matcher snMatcher = snPattern.matcher(texto);
        if (snMatcher.find()) {
            return "S/N";
        }

        // Buscando primeiramente por prefixos
        Pattern prefixoPattern = Pattern.compile("\\b(N[ÚU]MERO|N[°º]?|N.|NUM)\\s*(\\d{1,5})\\b");
        Matcher prefixoMatcher = prefixoPattern.matcher(texto);
        if (prefixoMatcher.find()) {
            return prefixoMatcher.group(2);
        }

        // Depois se não achar com prefixos busca pelo último número (já que existem ruas como Rua 9 de junho 400 ou algo assim)
        Pattern numeroPattern = Pattern.compile("\\b\\d{1,5}\\b");
        Matcher numeroMatcher = numeroPattern.matcher(texto);

        String ultimoNumero = "S/N";
        while (numeroMatcher.find()) {
            if (numeroMatcher.start() >= inicioBusca) {
                ultimoNumero = numeroMatcher.group();
            }
        }

        return ultimoNumero;
    }
}
