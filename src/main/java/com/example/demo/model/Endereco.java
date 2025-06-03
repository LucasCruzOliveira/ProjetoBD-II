package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
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

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fk_municipio")
    private Municipio municipio;

    @OneToMany
    private List<Empreendimento> empreendimento;
}
