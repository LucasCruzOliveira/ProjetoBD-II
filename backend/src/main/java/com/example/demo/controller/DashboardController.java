package com.example.demo.controller;

import com.example.demo.dto.EmpreendimentoPorSituacaoDTO;
import com.example.demo.dto.EmpreendimentosPorRegiaoDTO;
import com.example.demo.services.EmpreendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/financiamentoRegiao")
    public List<EmpreendimentosPorRegiaoDTO> buscarFinanciamentos(){
        return empreendimentoService.buscarFinanciamentosPorRegiao();
    }

    @GetMapping("/financiamentoSituacao")
    public List<EmpreendimentoPorSituacaoDTO> busacrFinanciamentosPorSituacao(){
        return empreendimentoService.buscarEmpreendimentoPorSituacao();
    }


}

