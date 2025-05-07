// package com.juriscontrol.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.juriscontrol.demo.dto.CredenciaisDTO;
// import com.juriscontrol.demo.exception.UsuarioNotFoundException;
// import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.model.enums.TipoUsuario;
// import com.juriscontrol.demo.service.UsuarioService;

// @RestController
// @RequestMapping("/usuarios")
// public class UsuarioController {

// @Autowired
// private UsuarioService usuarioService;

// //post cadastro usuario
// @PostMapping("/cadastrar")
// public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario)
// {

// return ResponseEntity.ok(usuarioService.cadastrar(usuario));
// }

// //post usuario administrador
// @PostMapping("/login/administrador")
// public ResponseEntity<Usuario> loginAdministrador(@RequestBody CredenciaisDTO
// credenciais) {

// try {
// Usuario usuario = usuarioService.login(credenciais.getEmail(),
// credenciais.getSenha(), TipoUsuario.ADMINISTRADOR);
// return ResponseEntity.ok(usuario);
// } catch (UsuarioNotFoundException e) {
// return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
// }
// }

// //post usuario advogado
// @PostMapping("/login/advogado")
// public ResponseEntity<Usuario> loginAdvogado(@RequestBody CredenciaisDTO
// credenciais) {

// try {
// Usuario usuario = usuarioService.login(credenciais.getEmail(),
// credenciais.getSenha(), TipoUsuario.ADVOGADO);
// return ResponseEntity.ok(usuario);
// } catch (UsuarioNotFoundException e) {
// return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
// }
// }
// }
