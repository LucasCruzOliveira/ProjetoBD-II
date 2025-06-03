package com.example.demo.model;

import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private UF uf;

    @Enumerated(EnumType.STRING)
    private Regiao regiao;

    @OneToMany(mappedBy = "municipio")
    private List<Endereco> endereco;
}
