package com.example.demo.dao;

import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoDAO extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByCepAndLogradouroAndNumero(String cep, String logadouro, String Numero);

}
