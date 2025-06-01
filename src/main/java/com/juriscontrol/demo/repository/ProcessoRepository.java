package com.juriscontrol.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorStatusDTO;
import com.juriscontrol.demo.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    long count(); // Para o total de processos

    @Query("SELECT new com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorAdvogadoDTO(a.nome, COUNT(p.id)) " +
            "FROM Processo p JOIN p.advogado a " +
            "GROUP BY a.nome " +
            "ORDER BY COUNT(p.id) DESC")
    List<ProcessosPorAdvogadoDTO> countProcessosByAdvogadoNome();

    @Query("SELECT new com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorStatusDTO(p.status, COUNT(p.id)) " +
            "FROM Processo p GROUP BY p.status")
    List<ProcessosPorStatusDTO> countProcessosByStatus();

    Optional<Processo> findByNumeroProcesso(String numeroProcesso);

    List<Processo> findByAdvogadoId(Long id);
}
