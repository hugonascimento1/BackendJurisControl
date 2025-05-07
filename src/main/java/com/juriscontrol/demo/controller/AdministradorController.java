package com.juriscontrol.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
import com.juriscontrol.demo.service.AdministradorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // post administrador
    @PostMapping("/cadastrar")
    public ResponseEntity<Administrador> cadastrarAdministrador(@RequestBody @Valid CriarAdministradorDTO dto) {
        Administrador administrador = administradorService.cadastrarAdministrador(dto);
        return new ResponseEntity<>(administrador, HttpStatus.CREATED);
    }

    // put administrador (ID)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Administrador> atualizarAdministrador(@PathVariable Long id,
            @RequestBody @Valid AtualizarAdministradorDTO dto) {
        try {
            Administrador administradorNovo = administradorService.atualizarAdministrador(id, dto);
            return ResponseEntity.ok(administradorNovo);
        } catch (AdministradorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get administrador por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ListaTudoAdministradorDTO> buscarPorIdAdministrador(@PathVariable Long id) {
        try {
            ListaTudoAdministradorDTO administrador = administradorService.buscarPorIdAdministrador(id);
            return ResponseEntity.ok(administrador);
        } catch (AdministradorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get administradores
    @GetMapping("/buscar-todos")
    public ResponseEntity<List<ListaTudoAdministradorDTO>> buscarTodosAdministradores() {
        List<ListaTudoAdministradorDTO> administradores = administradorService.buscarTodosAdministradores();
        return ResponseEntity.ok(administradores);
    }

    // delete administrador (ID)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAdministrador(@PathVariable Long id) {
        try {
            administradorService.deletarAdministrador(id);
            return ResponseEntity.noContent().build();
        } catch (AdministradorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
