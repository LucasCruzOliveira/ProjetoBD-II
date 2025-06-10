package com.example.demo.dao;

import com.example.demo.enums.Regiao;
import com.example.demo.enums.UF;
import com.example.demo.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MunicipioDAO extends JpaRepository<Municipio, UUID> {
    Optional<Municipio> findMunicipioByNomeAndUfAndRegiao(String nome, UF Uf, Regiao regiao);
}
