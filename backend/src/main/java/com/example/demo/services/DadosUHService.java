package com.example.demo.services;


import com.example.demo.dao.DadosUhDAO;
import com.example.demo.model.DadosUH;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class DadosUHService extends ParseService{
    private DadosUhDAO dadosUhDAO;

    public DadosUHService(CsvService csvService, DadosUhDAO dadosUhDAO) {
        super(csvService);
        this.dadosUhDAO = dadosUhDAO;
    }

    @Override
    public void parseToRelational(){
        for (CSVRecord csvRecord : csvService.getRecords()){
            int quantidade = Integer.parseInt(csvRecord.get("qtd_uh").trim());
            String textoEntregues = csvRecord.get("qtd_uh_entregues").trim().split(",")[0];
            int entregues = 0;
            if (textoEntregues != null && !textoEntregues.isEmpty()){
                entregues = (int) Double.parseDouble(textoEntregues);
            }

            int vigentes = Integer.parseInt(csvRecord.get("qtd_uh_vigentes").trim());
            int distratadas = Integer.parseInt(csvRecord.get("qtd_uh_distratadas").trim());

            DadosUH dadosUH = new DadosUH(quantidade, entregues, vigentes, distratadas);
            dadosUhDAO.save(dadosUH);
        }
    }
}
