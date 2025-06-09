package com.example.demo.dto;

import com.example.demo.enums.Modalidade;

public class EmpreendimentoPorModalidadeDTO {
    private Modalidade modalidade;
    private long total;

    public EmpreendimentoPorModalidadeDTO(Modalidade modalidade, long total) {
        this.modalidade = modalidade;
        this.total = total;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
