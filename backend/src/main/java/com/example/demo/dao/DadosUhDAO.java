package com.example.demo.dao;

import com.example.demo.model.DadosUH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface DadosUhDAO extends JpaRepository<DadosUH, UUID> {
}
