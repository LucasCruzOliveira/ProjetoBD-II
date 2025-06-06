package com.example.demo.services;

import com.example.demo.dao.MunicipioDAO;
import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Municipio;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;


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
            String nome = csvRecord.get("txt_nome_municipio").trim();
            UF uf = UF.valueOf(csvRecord.get("txt_sigla_uf").toUpperCase().trim());
            String regiao = csvRecord.get("txt_regiao").toUpperCase();
            if(regiao.equals("CENTRO-OESTE")){
                regiao = "CENTRO_OESTE";
            }
            Regiao regiaoEnum = Regiao.valueOf(regiao);
            Municipio municipio = new Municipio(nome, uf, regiaoEnum);
            municipioDAO.save(municipio);
        }
    }
}
