// package com.juriscontrol.demo.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.juriscontrol.demo.exception.UsuarioNotFoundException;
// import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.model.enums.TipoUsuario;
// import com.juriscontrol.demo.repository.UsuarioRepository;

// @Service
// public class UsuarioService {

// @Autowired
// private UsuarioRepository usuarioRepository;

// //cadastrar usuário (criar)
// public Usuario cadastrar(Usuario usuario) {
// return usuarioRepository.save(usuario);
// }

// //login usuário
// public Usuario login(String email, String senha, TipoUsuario tipo) throws
// UsuarioNotFoundException {
// return usuarioRepository.findByEmailAndSenhaAndTipo(email, senha, tipo)
// .orElseThrow(() -> new UsuarioNotFoundException("Credenciais inválidas ou
// tipo de usuário incorreto"));
// }

// public Usuario login(String email, String senha) throws
// UsuarioNotFoundException {
// return usuarioRepository.findByEmail(email)
// .orElseThrow(() -> new UsuarioNotFoundException("Credenciais inválidas"));

// /**return usuarioRepository.findAll().stream()
// .filter(usuario -> usuario.getEmail().equals(email) &&
// usuario.getSenha().equals(senha))
// .findFirst()
// .orElseThrow(() -> new UsuarioNotFoundException("Credenciais inválidas"));**/
// }

// //atualizar usuário
// public Usuario atualizar(Long id, Usuario usuarioAtualizado) throws
// UsuarioNotFoundException {

// Usuario usuario = usuarioRepository.findById(id)
// .orElseThrow(() -> new UsuarioNotFoundException("Usuário nao encontrado."));

// usuario.setNome(usuarioAtualizado.getNome());
// usuario.setEmail(usuarioAtualizado.getEmail());
// usuario.setSenha(usuarioAtualizado.getSenha());
// return usuarioRepository.save(usuario);
// }

// //buscar usuário por ID
// public Usuario buscarPorId(Long id) {
// Usuario usuario = usuarioRepository.findById(id)
// .orElseThrow(() -> new RuntimeException("Usuário nao encontrado."));

// return usuario;
// }

// //buscar todos os usuários
// public List<Usuario> buscarTodos() {
// return usuarioRepository.findAll();
// }

// //deletar usuário
// public void deletar(Long id) throws UsuarioNotFoundException {
// usuarioRepository.findById(id)
// .orElseThrow(() -> new UsuarioNotFoundException("Usuário nao encontrado."));

// usuarioRepository.deleteById(id);
// }
// }
