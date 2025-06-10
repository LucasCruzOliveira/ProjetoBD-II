package com.example.demo.dao;

import com.example.demo.dto.EnderecosDTO;
import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoDAO extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByCepAndLogradouroAndNumero(String cep, String logadouro, String Numero);

    @Query("SELECT new com.example.demo.dto.EnderecosDTO(e.logradouro, e.bairro, e.numero, e.cep, e.municipio, e.municipio.uf, e.municipio.regiao, e.texto) FROM Endereco e")
    List<EnderecosDTO> buscarEnderecos();

}
