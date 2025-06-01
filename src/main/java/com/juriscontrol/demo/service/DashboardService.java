package com.juriscontrol.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorStatusDTO;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class DashboardService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    public long getTotalProcessos() {
        return processoRepository.count();
    }

    public long getTotalAdvogados() {
        return advogadoRepository.count();
    }

    public List<ProcessosPorAdvogadoDTO> getProcessosPorAdvogado() {
        return processoRepository.countProcessosByAdvogadoNome();
    }

    public List<ProcessosPorStatusDTO> getProcessosPorStatus() {
        return processoRepository.countProcessosByStatus();
    }

}
