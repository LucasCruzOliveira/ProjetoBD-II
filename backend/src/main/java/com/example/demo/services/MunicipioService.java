package com.example.demo.services;

import com.example.demo.dao.MunicipioDAO;
import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Endereco;
import com.example.demo.model.Municipio;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MunicipioService extends ParseService {
    private MunicipioDAO municipioDAO;

    public MunicipioService(CsvService csvService, MunicipioDAO municipioDAO) {
        super(csvService);
        this.municipioDAO = municipioDAO;
    }

    @Override
    public void parseToRelational() {

        for(CSVRecord csvRecord  : csvService.getRecords()) {
            String nome = csvRecord.get("txt_nome_municipio").toUpperCase().trim();
            UF uf = UF.valueOf(csvRecord.get("txt_sigla_uf").toUpperCase().trim());
            String regiao = csvRecord.get("txt_regiao").toUpperCase().trim();
            if(regiao.equals("CENTRO-OESTE")){
                regiao = "CENTRO_OESTE";
            }
            Regiao regiaoEnum = Regiao.valueOf(regiao);
            criar(nome, uf, regiaoEnum);
        }
    }
    public Municipio criar(String nome, UF uf, Regiao regiao){
        Optional<Municipio> municipioOptional = municipioDAO.findMunicipioByNomeAndUfAndRegiao(nome, uf, regiao);
        if(!municipioOptional.isPresent()){
            return municipioDAO.save(new Municipio(nome, uf, regiao));
        }
        return null;
    }
}
