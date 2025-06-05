package com.example.demo.dao;

import com.example.demo.model.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpreendimentoDAO extends JpaRepository<Empreendimento, UUID> {
}
