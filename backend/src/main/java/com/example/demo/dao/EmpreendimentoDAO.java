package com.example.demo.dao;

import com.example.demo.dto.EmpreendimentoPorSituacaoDTO;
import com.example.demo.dto.EmpreendimentosPorRegiaoDTO;
import com.example.demo.model.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmpreendimentoDAO extends JpaRepository<Empreendimento, UUID> {
    @Query("SELECT new com.example.demo.dto.EmpreendimentosPorRegiaoDTO(e.endereco.municipio.regiao, COUNT(DISTINCT e.id)) FROM Empreendimento e GROUP BY e.endereco.municipio.regiao")
    List<EmpreendimentosPorRegiaoDTO> buscarEmpreendimentosPorRegiao();

    @Query("SELECT new com.example.demo.dto.EmpreendimentoPorSituacaoDTO(e.situacao, COUNT(DISTINCT e.id)) FROM Empreendimento e GROUP BY e.situacao")
    List<EmpreendimentoPorSituacaoDTO> buscarEmpreendimentoPorSituacao();
}
