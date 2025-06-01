package com.juriscontrol.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
    List<Processo> findByAdvogadoId(Long id);
}
