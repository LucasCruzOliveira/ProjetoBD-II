package com.example.demo.services;
import com.example.demo.dao.ConstrutoraDAO;
import com.example.demo.model.Construtora;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class ConstrutoraService extends ParseService{
    private ConstrutoraDAO construtoraDAO;

    public ConstrutoraService(CsvService csvService, ConstrutoraDAO construtoraDAO) {
        super(csvService);
        this.construtoraDAO = construtoraDAO;
    }

    @Override
    public void parseToRelational() {

        for(CSVRecord csvRecord : csvService.getRecords()){
            String nome = csvRecord.get("txt_nome_construtora_entidade").trim();
            String cnpj = csvRecord.get("txt_cnpj_construtora_entidade");

            if(nome.isBlank() || nome.equals("Não informado")){
                nome = ("DESCONHECIDO");
            }
            if(cnpj.isBlank()){
                cnpj = ("NÃO INFORMADO");
            }
            Construtora construtora = new Construtora(nome, cnpj);
            construtoraDAO.save(construtora);


        }
    }
}
