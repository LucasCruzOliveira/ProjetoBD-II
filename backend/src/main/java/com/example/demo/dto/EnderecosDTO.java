package com.example.demo.dto;

import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Municipio;

public class EnderecosDTO {
    private String logradouro;
    private String bairro;
    private String numero;
    private String CEP;
    private Municipio municipio;
    private UF uf;
    private Regiao regiao;
    private String complemento;
    private String Texto;

    public EnderecosDTO(String logradouro, String bairro, String numero, String CEP, Municipio municipio, UF uf, Regiao regiao, String complemento, String texto) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.CEP = CEP;
        this.municipio = municipio;
        this.uf = uf;
        this.regiao = regiao;
        this.complemento = complemento;
        Texto = texto;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCEP() {
        return CEP;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public UF getUf() {
        return uf;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getTexto() {
        return Texto;
    }
}