package com.example.demo.controller;

import com.example.demo.dto.EmpreendimentoPorLogradouroDTO;
import com.example.demo.dto.EmpreendimentoPorModalidadeDTO;
import com.example.demo.dto.EmpreendimentoPorSituacaoDTO;
import com.example.demo.dto.EmpreendimentosPorRegiaoDTO;
import com.example.demo.services.EmpreendimentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private EmpreendimentoService empreendimentoService;

    public DashboardController(EmpreendimentoService empreendimentoService) {
        this.empreendimentoService = empreendimentoService;
    }

    @GetMapping("/empreendimentos")
    public List<EmpreendimentosPorRegiaoDTO> buscarEmpreendimentosPorRegiao(){
        return empreendimentoService.buscarFinanciamentosPorRegiao();
    }

    @GetMapping("/situacoes")
    public List<EmpreendimentoPorSituacaoDTO> buscarEmpreendimentoPorSituacao(){
        return empreendimentoService.buscarEmpreendimentoPorSituacao();
    }

    @GetMapping("/modalidades")
    public List<EmpreendimentoPorModalidadeDTO> buscarEmpreendimentoPorModalidade(){
        return  empreendimentoService.buscarEmpreendimentoPorModalidade();
    }

    @GetMapping("/logradouros")
    public List<EmpreendimentoPorLogradouroDTO> buscarEmpreendimentoPorLogradouro(){
        return empreendimentoService.buscarEmpreendimentoPorLogradouro();
    }

}

