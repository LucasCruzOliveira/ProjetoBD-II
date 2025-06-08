package com.example.demo.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public abstract class ParseService {
    protected CsvService csvService;

    protected ParseService(CsvService csvService){
        this.csvService = csvService;
    }
}
