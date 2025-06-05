package com.example.demo.model;

import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
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

    public Municipio(String nome, UF uf, Regiao regiao) {
        this.nome = nome;
        this.uf = uf;
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf=" + uf +
                ", regiao=" + regiao +
                '}';
    }
}
