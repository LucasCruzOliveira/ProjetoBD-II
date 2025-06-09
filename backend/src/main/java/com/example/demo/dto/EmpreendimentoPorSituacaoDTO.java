package com.example.demo.dto;

import com.example.demo.enums.Situacao;

public class EmpreendimentoPorSituacaoDTO {
    private Situacao situacao;
    private long total;

    public EmpreendimentoPorSituacaoDTO(Situacao situacao, long total) {
        this.situacao = situacao;
        this.total = total;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public long getTotal() {
        return total;
    }
}
