package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.AgendaTarefa;

@Repository
public interface AgendaTarefaRepository extends JpaRepository<AgendaTarefa, Long> {
    
    List<AgendaTarefa> findByAdvogadoId(Long advogadoId);
}