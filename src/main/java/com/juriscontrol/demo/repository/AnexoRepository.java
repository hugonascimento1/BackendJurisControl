package com.juriscontrol.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Anexo;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
    
    List<Anexo> findByProcessoId(Long processoId);
}
