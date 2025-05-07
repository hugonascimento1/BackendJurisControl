package com.juriscontrol.demo.controller;

import com.juriscontrol.demo.dto.CredenciaisDTO;
import com.juriscontrol.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login/admin")
    public ResponseEntity<?> autenticarAdmin(@RequestBody CredenciaisDTO credenciais) {
        System.out.println("Tentativa de login de administrador: Email - " + credenciais.getEmail());

        if (credenciais.getEmail() == null || credenciais.getSenha() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email e senha são obrigatórios"));
        }

        try {
            String token = authService.authenticateAdmin(credenciais);
            return ResponseEntity.ok(Map.of("token", token, "tipoUsuario", "administrador"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login/advogado")
    public ResponseEntity<?> autenticarAdvogado(@RequestBody CredenciaisDTO credenciais) {
        System.out.println("Tentantiva de login de advogado:  Email - " + credenciais.getEmail());

        if (credenciais.getEmail() == null || credenciais.getSenha() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email e senha são obrigatórios"));
        }

        try {
            String token = authService.authenticateAdvogado(credenciais);
            return ResponseEntity.ok(Map.of("token", token, "tipoUsuario", "advogado"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    // // Endpoint para login
    // @PostMapping("/login")
    // public ResponseEntity<?> autenticar(@RequestBody CredenciaisDTO credenciais)
    // {
    // System.out.println("Credenciais recebidas: Email - " +
    // credenciais.getEmail());

    // if (credenciais.getEmail() == null || credenciais.getSenha() == null) {
    // return ResponseEntity.badRequest().body(Map.of("error", "Email e senha são
    // obrigatórios"));
    // }

    // try {
    // String token = authService.authenticate(credenciais);
    // return ResponseEntity.ok(Map.of("token", token));
    // } catch (RuntimeException e) {
    // return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
    // }
    // }
}