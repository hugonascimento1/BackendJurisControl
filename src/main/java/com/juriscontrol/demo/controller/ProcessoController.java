package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.service.ProcessoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/processos")
public class ProcessoController {
    
    @Autowired
    private ProcessoService processoService;

    //post processo
    @PostMapping("/criar")
    public ResponseEntity<Processo> adicionarProcesso(CriarProcessoDTO dto) {
        
        try {
            Processo processoNovo = processoService.criarProcesso(dto);
            return ResponseEntity.ok(processoNovo);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //put processo (ID)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Processo> atualizarProcesso(@PathVariable Long id, @RequestBody AtualizarProcessoDTO dto) {
        
        try {
            Processo processoAtualizado = processoService.atualizarProcesso(id, dto);
            return ResponseEntity.ok(processoAtualizado);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //get processo por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ListaTudoProcessoDTO> buscarProcessoPorId(@PathVariable Long id) {
        
        try {
            ListaTudoProcessoDTO processo = processoService.buscarPorIdProcesso(id);
            return ResponseEntity.ok(processo);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //get processos
    @GetMapping("/buscarTodos/processos")
    public ResponseEntity<List<ListaTudoProcessoDTO>> buscarTodosProcessos() {
        
        List<ListaTudoProcessoDTO> processos = processoService.buscarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    //get processo por número (ID)
    @GetMapping("/buscarPorNumero/{numero}")
    public ResponseEntity<ListaProcessoDTO> buscarPorNumeroDeProcesso(@PathVariable String numero) {
        
        try {
            ListaProcessoDTO processo = processoService.buscarPorNumeroProcesso(numero);
            return ResponseEntity.ok(processo);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //delete processo (ID)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProcesso(@PathVariable Long id) {
        
        try {
            processoService.deletarProcesso(id);
            return ResponseEntity.ok("Processo deletado com sucesso.");
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}