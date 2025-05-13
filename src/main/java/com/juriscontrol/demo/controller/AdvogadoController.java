package com.juriscontrol.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AdvogadoDTO.AtualizarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.CriarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.service.AdvogadoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvogadoController {

    @Autowired
    private AdvogadoService advogadoService;

    // post advogado
    @PostMapping("/cadastrar-advogado")
    public ResponseEntity<Advogado> cadastrarAdvogado(@RequestBody @Valid CriarAdvogadoDTO dto) {
        Advogado advogadoNovo = advogadoService.cadastrarAdvogado(dto);
        return new ResponseEntity<>(advogadoNovo, HttpStatus.CREATED);
    }

    // put advogado (ID)
    @PutMapping("/atualizar-advogado/{id}")
    public ResponseEntity<Advogado> atualizarAdvogado(@PathVariable Long id,
            @RequestBody @Valid AtualizarAdvogadoDTO dto) {
        try {
            Advogado advogadoAtualizado = advogadoService.atualizarAdvogado(id, dto);
            return ResponseEntity.ok(advogadoAtualizado);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get advogado por ID
    @GetMapping("/buscar-advogado/{id}")
    public ResponseEntity<ListaTudoAdvogadoDTO> buscarPorIdAdvogado(@PathVariable Long id) {
        try {
            ListaTudoAdvogadoDTO advogado = advogadoService.buscarPorIdAdvogado(id);
            return ResponseEntity.ok(advogado);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get advogados
    @GetMapping("/buscar-todos-advogados")
    public ResponseEntity<List<ListaTudoAdvogadoDTO>> buscarTodosAdvogados() {
        List<ListaTudoAdvogadoDTO> advogados = advogadoService.buscarTodosAdvogados();
        return ResponseEntity.ok(advogados);
    }

    @GetMapping("/buscar-todos-info-resumido-advogados")
    public ResponseEntity<List<ListaAdvogadoDTO>> buscarTodosAdvogadosResumo() {
        List<ListaAdvogadoDTO> advogados = advogadoService.buscarTodosAdvogadosResumo();
        return ResponseEntity.ok(advogados);
    }

    // get advogado resumido (ID)
    @GetMapping("/buscar-advogado-resumido/{id}")
    public ResponseEntity<ListaAdvogadoDTO> buscarPorIdAdvogadoResumido(@PathVariable Long id) {
        try {
            ListaAdvogadoDTO advogado = advogadoService.buscarPorIdAdvogadoResumo(id);
            return ResponseEntity.ok(advogado);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // delete advogado (ID)
    @DeleteMapping("/deletar-advogado/{id}")
    public ResponseEntity<String> deletarAdvogado(@PathVariable Long id) {
        try {
            advogadoService.deletarAdvogado(id);
            return ResponseEntity.noContent().build();
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
