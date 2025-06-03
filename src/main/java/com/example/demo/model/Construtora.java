package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Construtora {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column (nullable = false, length = 14)
    private String cnpj;

    @OneToMany
    @JoinColumn(name = "fk_empreendimento")
    private List<Empreendimento> empreendimento;

}
