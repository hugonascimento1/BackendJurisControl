package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.ListaAgendaTarefaDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.AgendaTarefaNotFoundException;
import com.juriscontrol.demo.model.AgendaTarefa;
import com.juriscontrol.demo.service.AgendaTarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AgendaTarefaController {
    
    @Autowired
    private AgendaTarefaService agendaTarefaService;

    //post tarefa
    @PostMapping("/cadastrar-tarefa")
    public ResponseEntity<AgendaTarefa> adicionarAgendaTarefa(@RequestBody CriarAgendaTarefaDTO dto) {
        
        try {
            AgendaTarefa tarefaNova = agendaTarefaService.criarAgendaTarefa(dto);
            return ResponseEntity.ok(tarefaNova);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //put tarefa (ID)
    @PutMapping("/atualizar-tarefa/{id}")
    public ResponseEntity<AgendaTarefa> atualizarAgendaTarefa(@PathVariable Long id, @Valid @RequestBody AtualizarAgendaTarefaDTO tarefa) {
        
        try {
            AgendaTarefa tarefaAtualizada = agendaTarefaService.atualizarAgendaTarefa(id, tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (AgendaTarefaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (AdvogadoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //get tarefa por ID
    @GetMapping("/buscar-tarefa/{id}")
    public ResponseEntity<ListaAgendaTarefaDTO> buscarPorIdAgendaTarefa(@PathVariable Long id) {

        try {
            ListaAgendaTarefaDTO tarefa = agendaTarefaService.buscarPorIdAgendaTarefa(id);
            return ResponseEntity.ok(tarefa);
        } catch (AgendaTarefaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //get tarefas
    @GetMapping("/buscar-todas-tarefas")
    public ResponseEntity<List<ListaAgendaTarefaDTO>> buscarTodasTarefas() {
        
        List<ListaAgendaTarefaDTO> tarefas = agendaTarefaService.buscarTodasAgendaTarefa();
        return ResponseEntity.ok(tarefas);
    }

    //get tarefas por ID
    @GetMapping("/buscar-todas-tarefas/{id}")
    public ResponseEntity<List<ListaAgendaTarefaDTO>> buscarTodasTarefasPorId(@PathVariable Long id) {
        
        List<ListaAgendaTarefaDTO> tarefas = agendaTarefaService.buscarTodasAgendaTarefaPorId(id);
        return ResponseEntity.ok(tarefas);
    }

    //delete tarefa (ID)
    @DeleteMapping("/deletar-tarefa/{id}")
    public ResponseEntity<String> deletarAgendaTarefa(@PathVariable Long id) {

        try {
            agendaTarefaService.deletarAgendaTarefa(id);
            return ResponseEntity.ok("Tarefa deletada com sucesso.");
        } catch (AgendaTarefaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
