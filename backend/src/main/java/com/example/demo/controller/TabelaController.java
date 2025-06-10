package com.example.demo.controller;

import com.example.demo.dto.EnderecosDTO;
import com.example.demo.services.EnderecoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tabela")
public class TabelaController {
    EnderecoService enderecoService;

    public TabelaController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/enderecos")
    public List<EnderecosDTO> buscarEnderecos(){
        return enderecoService.buscarEnderecos();
    }
}
