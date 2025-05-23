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

import com.juriscontrol.demo.dto.MovimentoDTO.AtualizarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.CriarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.ListaMovimentoDTO;
import com.juriscontrol.demo.exception.MovimentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Movimento;
import com.juriscontrol.demo.service.MovimentoService;

@RestController
@RequestMapping("/api")
public class MovimentoController {
    
    @Autowired
    private MovimentoService movimentoService;

        //post movimento
        @PostMapping("/cadastrar-movimento")
        public ResponseEntity<Movimento> adicionarMovimento(@RequestBody CriarMovimentoDTO dto) {
            
            try {
                Movimento movimentoNovo = movimentoService.criarMovimento(dto);
                return ResponseEntity.ok(movimentoNovo);
            } catch (ProcessoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        //put movimento (ID)
        @PutMapping("/atualizar-movimento/{id}")
        public ResponseEntity<Movimento> atualizarMovimento(@PathVariable Long id, @RequestBody AtualizarMovimentoDTO dto) {
            
            try {
                Movimento movimento = movimentoService.atualizarMovimento(id, dto);
                return ResponseEntity.ok(movimento);
            } catch (ProcessoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } catch (MovimentoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    
        //get movimento por ID
        @GetMapping("/buscar-movimento/{id}")
        public ResponseEntity<ListaMovimentoDTO> buscarPorIdMovimento(@PathVariable Long id) {
            
            try {
                ListaMovimentoDTO movimento = movimentoService.buscarPorIdMovimento(id);
                return ResponseEntity.ok(movimento);
            } catch (MovimentoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    
        //get movimentos
        @GetMapping("/buscar-todos-movimentos")
        public ResponseEntity<List<ListaMovimentoDTO>> buscarTodosMovimentos() {
            
            List<ListaMovimentoDTO> movimentos = movimentoService.buscarTodosMovimentos();
            return ResponseEntity.ok(movimentos);
        }

        //get movimentos (ID)
        @GetMapping("/buscar-todos-movimentos/{id}")
        public ResponseEntity<List<ListaMovimentoDTO>> buscarTodosMovimentosPorId(@PathVariable Long id) {
            
            List<ListaMovimentoDTO> movimentos = movimentoService.buscarTodosMovimentosPorId(id);
            return ResponseEntity.ok(movimentos);
        }
    
        //delete movimento (ID)
        @DeleteMapping("/deletar-movimento/{id}")
        public ResponseEntity<String> deletarMovimento(@PathVariable Long id) {
            
            try {
                movimentoService.deletarMovimento(id);
                return ResponseEntity.ok("Movimento deletado com sucesso.");
            } catch (MovimentoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
}
