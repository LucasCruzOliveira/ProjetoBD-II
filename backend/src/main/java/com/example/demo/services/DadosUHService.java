package com.example.demo.services;


import com.example.demo.dao.DadosUhDAO;
import com.example.demo.model.DadosUH;
import org.springframework.stereotype.Service;

@Service
public class DadosUHService extends ParseService{
    private DadosUhDAO dadosUhDAO;

    public DadosUHService(CsvService csvService, DadosUhDAO dadosUhDAO) {
        super(csvService);
        this.dadosUhDAO = dadosUhDAO;
    }

    public DadosUH parseToRelational(String quantidadeTxt, String entreguesTxt, String vigentesTxt, String distradasTxt ){
        int quantidade = Integer.parseInt(quantidadeTxt);
        int entregues = 0;
        if (entreguesTxt != null && !entreguesTxt.isEmpty()){
            entreguesTxt = entreguesTxt.split(",")[0];
            entregues = (int) Double.parseDouble(entreguesTxt);
        }
        int vigentes = Integer.parseInt(vigentesTxt);
        int distratadas = Integer.parseInt(distradasTxt);
        DadosUH dadosUH = new DadosUH(quantidade, entregues, vigentes, distratadas);
        return dadosUhDAO.save(dadosUH);
    }
}
