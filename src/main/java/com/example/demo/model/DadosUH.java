package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DadosUH {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private int entregues;

    @Column(nullable = false)
    private int vigentes;

    @Column(nullable = false)
    private int distratadas;

}
