package com.example.demo.services;

import com.example.demo.dao.MunicipioDAO;
import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Municipio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MunicipioService extends ParseService {
    private MunicipioDAO municipioDAO;

    public MunicipioService(CsvService csvService, MunicipioDAO municipioDAO) {
        super(csvService);
        this.municipioDAO = municipioDAO;
    }

    public Municipio parseToRelational(String nome, String uf, String regiao) {
        UF UFEnum = UF.valueOf(uf);
        if(regiao.equals("CENTRO-OESTE")){
            regiao = "CENTRO_OESTE";
        }
        Regiao regiaoEnum = Regiao.valueOf(regiao);
        Municipio municipio = criar(nome, UFEnum, regiaoEnum);
        return municipio;
    }

    public Municipio criar(String nome, UF uf, Regiao regiao){
        Optional<Municipio> municipioOptional = municipioDAO.findMunicipioByNomeAndUfAndRegiao(nome, uf, regiao);
        if(municipioOptional.isPresent()){
            return municipioOptional.get();
        }
        return municipioDAO.save(new Municipio(nome, uf, regiao));
    }



    public Map<String, Long> contarEstados(){
        return municipioDAO.findAll().stream()
                .collect(Collectors.groupingBy(
                        m -> m.getUf().toString(),
                        Collectors.counting()
                ));
    }
}
