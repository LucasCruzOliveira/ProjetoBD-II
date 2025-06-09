package com.example.demo.dto;

import com.example.demo.enums.Modalidade;
import com.example.demo.enums.Situacao;
import com.example.demo.enums.UF;
import com.example.demo.model.Construtora;
import com.example.demo.model.Municipio;

import java.math.BigDecimal;

public class EmpreendimentoDTO {
    private String nome;
    private Situacao situacao;
    private Modalidade modalidade;
    private Municipio municipio;
    private UF uf;
    private Construtora construtora;
    private BigDecimal valor;

    public EmpreendimentoDTO(String nome, Situacao situacao, Modalidade modalidade, Municipio municipio, UF uf, Construtora construtora, BigDecimal valor) {
        this.nome = nome;
        this.situacao = situacao;
        this.modalidade = modalidade;
        this.municipio = municipio;
        this.uf = uf;
        this.construtora = construtora;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public UF getUf() {
        return uf;
    }

    public Construtora getConstrutora() {
        return construtora;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
