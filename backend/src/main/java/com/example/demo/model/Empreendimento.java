package com.example.demo.model;

import com.example.demo.enums.Modalidade;
import com.example.demo.enums.Situacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Empreendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private LocalDate dataAssinatura;
    @Column(nullable = false,length = 8)
    private String codigoOperacao;
    private BigDecimal valorDesembolsado;
    private BigDecimal valorTotalContratado;

    @ManyToOne
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;

    @OneToOne
    @JoinColumn(name = "fk_dadosUH")
    private DadosUH dadosUH;

    @ManyToOne
    @JoinColumn(name = "fk_construtora")
    private Construtora construtora;

    public Empreendimento(String nome, Situacao situacao, LocalDate dataAssinatura, String codigoOperacao, BigDecimal valorDesembolsado, BigDecimal valorTotalContratado, Modalidade modalidade) {
        this.nome = nome;
        this.situacao = situacao;
        this.dataAssinatura = dataAssinatura;
        this.codigoOperacao = codigoOperacao;
        this.valorDesembolsado = valorDesembolsado;
        this.valorTotalContratado = valorTotalContratado;
        this.modalidade = modalidade;
    }
}
