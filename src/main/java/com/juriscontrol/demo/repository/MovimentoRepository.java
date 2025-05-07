package com.juriscontrol.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    
}
