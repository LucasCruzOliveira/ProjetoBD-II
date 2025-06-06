package com.example.demo.services;

import com.example.demo.dao.EnderecoDAO;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends ParseService {
    private EnderecoDAO enderecoDAO;

    public EnderecoService(CsvService csvService, EnderecoDAO enderecoDAO){
        super(csvService);
        this.enderecoDAO = enderecoDAO;
    }
    
    @Override
    public void parseToRelational() {

    }
}
