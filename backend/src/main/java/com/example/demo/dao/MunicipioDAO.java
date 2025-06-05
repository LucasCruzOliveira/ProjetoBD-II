package com.example.demo.dao;

import com.example.demo.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MunicipioDAO extends JpaRepository<Municipio, UUID> {

}
