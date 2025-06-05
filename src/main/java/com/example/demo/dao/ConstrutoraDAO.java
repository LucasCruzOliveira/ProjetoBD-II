package com.example.demo.dao;

import com.example.demo.model.Construtora;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConstrutoraDAO extends JpaRepository<Construtora, UUID> {

}
