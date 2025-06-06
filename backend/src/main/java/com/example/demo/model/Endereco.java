package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String numero;

    private String texto;

    @Column(nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fk_municipio")
    private Municipio municipio;

    @OneToMany
    private List<Empreendimento> empreendimento;

    public Endereco(String logradouro, String bairro, String numero, String texto, String cep, Municipio municipio) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.texto = texto;
        this.cep = cep;
        this.municipio = municipio;
    }
}
