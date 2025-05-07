package com.juriscontrol.demo.controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juriscontrol.demo.dto.AnexoDTO.AtualizarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarVariosAnexosDTO;
import com.juriscontrol.demo.dto.AnexoDTO.ListaAnexoDTO;
import com.juriscontrol.demo.exception.AnexoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.service.AnexoService;


@RestController
@RequestMapping("/anexos")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

    @PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Anexo> adicionarAnexo(@ModelAttribute CriarAnexoDTO dto) {
        try {
            Anexo anexoNovo = anexoService.criarAnexo(dto);
            return ResponseEntity.ok(anexoNovo);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // post anexo
    // @PostMapping(value = "/criar", consumes =
    // MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Anexo> adicionarAnexo(CriarAnexoDTO dto) {

    // try {
    // Anexo anexoNovo = anexoService.criarAnexo(dto);
    // return ResponseEntity.ok(anexoNovo);
    // } catch (ProcessoNotFoundException e) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    // } catch (IOException e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    // }
    // }

    // post varios anexos
    @PostMapping(value = "/cadastrar-varios", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Anexo>> adicionarVariosAnexos(@ModelAttribute CriarVariosAnexosDTO dtos) {
        try {
            List<Anexo> anexosNovos = anexoService.criarVariosAnexos(dtos);
            return ResponseEntity.ok(anexosNovos);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // put anexo (ID)
    @PutMapping("/atualizar{id}")
    public ResponseEntity<Anexo> atualizarAnexo(@PathVariable Long id, AtualizarAnexoDTO dto) {
        try {
            Anexo anexoNovo = anexoService.atualizarAnexo(id, dto);
            return ResponseEntity.ok(anexoNovo);
        } catch (AnexoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ProcessoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // get anexo por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ListaAnexoDTO> buscarAnexoPorId(@PathVariable Long id) {

        try {
            ListaAnexoDTO anexo = anexoService.buscarAnexoPorId(id);
            return ResponseEntity.ok(anexo);
        } catch (AnexoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // get anexos
    @GetMapping("/buscar-todos")
    public ResponseEntity<List<ListaAnexoDTO>> buscarTodosAnexos() {

        List<ListaAnexoDTO> anexos = anexoService.buscarTodosAnexos();
        return ResponseEntity.ok(anexos);
    }

    // get baixar anexo (ID)
    @GetMapping("/baixar/{id}")
    public ResponseEntity<byte[]> baixarAnexo(@PathVariable Long id) {
        try {
            Anexo anexo = anexoService.baixarAnexo(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + anexo.getNomeAnexo() + "\"")
                    .contentType(MediaType.parseMediaType(anexo.getTipoAnexo()))
                    .body(anexo.getAnexo());
        } catch (AnexoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // delete anexo (ID)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAnexo(@PathVariable Long id) {

        try {
            anexoService.deletarAnexo(id);
            return ResponseEntity.ok("Anexo deletado com sucesso.");
        } catch (AnexoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
