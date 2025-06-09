package com.example.demo.dto;

import com.example.demo.enums.Regiao;

public class EmpreendimentosPorRegiaoDTO {
    private Regiao regiao;
    private Long total;

    public EmpreendimentosPorRegiaoDTO(Regiao regiao, Long total) {
        this.regiao = regiao;
        this.total = total;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public Long getTotal() {
        return total;
    }
}
