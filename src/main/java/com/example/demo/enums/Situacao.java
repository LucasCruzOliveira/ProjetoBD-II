package com.example.demo.enums;

public enum Situacao {
    CONCLUIDO("Concluído"),
    CANCELADO("Distratado/Cancelado"),
    NAO_CONCLUIDO("Não Concluído");


    private String desc;

    private Situacao(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
