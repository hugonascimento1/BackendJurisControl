package com.juriscontrol.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorAdvogadoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ProcessosPorStatusDTO;
import com.juriscontrol.demo.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/total-processos")
    public ResponseEntity<Map<String, Long>> getTotalProcessos() {
        long totalProcessos = dashboardService.getTotalProcessos();
        Map<String, Long> response = new HashMap<>();
        response.put("totalProcessos", totalProcessos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-advogados")
    public ResponseEntity<Map<String, Long>> getTotalAdvogados() {
        long totalAdvogados = dashboardService.getTotalAdvogados();
        Map<String, Long> response = new HashMap<>();
        response.put("totalAdvogados", totalAdvogados);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/processos-por-advogado")
    public ResponseEntity<List<ProcessosPorAdvogadoDTO>> getProcessosPorAdvogado() {
        List<ProcessosPorAdvogadoDTO> data = dashboardService.getProcessosPorAdvogado();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/processos-por-status")
    public ResponseEntity<List<ProcessosPorStatusDTO>> getProcessosPorStatus() {
        List<ProcessosPorStatusDTO> data = dashboardService.getProcessosPorStatus();
        return ResponseEntity.ok(data);
    }

}
