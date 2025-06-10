package com.example.demo.services;
import com.example.demo.dao.ConstrutoraDAO;
import com.example.demo.model.Construtora;
import org.springframework.stereotype.Service;

@Service
public class ConstrutoraService extends ParseService{
    private ConstrutoraDAO construtoraDAO;

    public ConstrutoraService(CsvService csvService, ConstrutoraDAO construtoraDAO) {
        super(csvService);
        this.construtoraDAO = construtoraDAO;
    }

    public Construtora parseToRelational(String nome, String cnpj) {
            if(nome.isBlank() || nome.equals("Não informado")){
                nome = ("DESCONHECIDO");
            }
            if(cnpj.isBlank()){
                cnpj = ("NÃO INFORMADO");
            }
            Construtora construtora = new Construtora(nome, cnpj);
            return construtoraDAO.save(construtora);
    }

}
