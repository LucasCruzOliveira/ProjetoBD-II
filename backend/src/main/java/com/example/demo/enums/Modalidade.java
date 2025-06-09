package com.example.demo.enums;


public enum Modalidade {
    FAR("FAR"),
    ENTIDADES("ENTIDADES"),
    OFERTA_PUBLICA("OFERTA PÚBLICA"),
    RURAL("RURAL"),
    FNHIS("FNHIS");

    private String desc;

    private Modalidade(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
