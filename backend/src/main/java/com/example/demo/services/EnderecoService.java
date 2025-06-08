package com.example.demo.services;

import com.example.demo.dao.EnderecoDAO;
import com.example.demo.dao.MunicipioDAO;
import com.example.demo.enums.Regiao;
import com.example.demo.model.Endereco;
import com.example.demo.model.Municipio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class EnderecoService extends ParseService {

    private final MunicipioDAO municipioDAO;
    private  MunicipioService municipioService;
    private EnderecoDAO enderecoDAO;
    private List<Pattern> patterns;

    public EnderecoService(CsvService csvService, MunicipioService municipioService, EnderecoDAO enderecoDAO, MunicipioDAO municipioDAO) {
        super(csvService);
        this.municipioService = municipioService;
        this.enderecoDAO = enderecoDAO;
        this.municipioDAO = municipioDAO;
    }

    public Endereco parseToRelational(String texto, String cep, String nomeMunicipio, String regiao, String uf ) {

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

        Endereco endereco = null;
        Municipio municipio = municipioService.parseToRelational(nomeMunicipio, uf, regiao);
        for(Pattern pattern : patterns){
            endereco = buscarEnderecoPorRegex(pattern, texto, cep, municipio, regiao);
            if(endereco != null) {
                break;
            }
        }
        if(endereco == null){
            endereco = new  Endereco("NÃO IDENTIFICADO", "SEM BAIRRO", "S/N", texto, cep, municipio);
        }
        return enderecoDAO.save(endereco);
    }

    public Endereco buscarEnderecoPorRegex(Pattern pattern, String texto, String cep, Municipio municipio, String regiao){
        Map<String,  String> map = Map.of(
                "R", "RUA",
                "AV", "AVENIDA",
                "TRAV", "TRAVESSA"
        );

        if(regiao.equals("CENTRO-OESTE")){
            regiao = "CENTRO_OESTE";
        }
        Regiao regiaoEnum = Regiao.valueOf(regiao);

        if(cep.length() != 8){
            cep = "DESCONHECIDO";
        }
        String bairro = buscarBairro(texto);

        Matcher matcher = pattern.matcher(texto);

        if(matcher.find()){
            try{
                String numero = buscarNumero(texto, matcher.end(1));
                String tipo = matcher.group(1);
                String logradouro = matcher.group();
                if(map.get(tipo) != null){
                    logradouro = logradouro.replaceAll(tipo, map.get(tipo));
                }
                logradouro.replace("S/N", "")
                        .replaceAll("(\\b(SN|S/N|NR|N\\.|N°|N)\\b.*)", "");


                return acharOuCriarEndereco(logradouro, bairro, numero, texto, cep, municipio);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public Endereco acharOuCriarEndereco(String logradouro, String bairro, String numero, String texto, String cep, Municipio municipio){
        Optional<Endereco> enderecoOptional = enderecoDAO.findByCepAndLogradouroAndNumero(cep, logradouro, numero);
        if(enderecoOptional.isPresent()){
           return enderecoOptional.get();
        }
        return enderecoDAO.save(new Endereco(logradouro, bairro, numero,texto, cep, municipio));
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
        Pattern prefixoPattern = Pattern.compile("\\b(N[ÚU]MERO|N[°º]?|N\\.|NUM)\\s*(\\d{1,5})\\b");
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
