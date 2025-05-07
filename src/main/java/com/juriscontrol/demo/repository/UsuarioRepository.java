// package com.juriscontrol.demo.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.model.enums.TipoUsuario;

// @Repository
// public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

// Optional<Usuario> findByEmailAndSenhaAndTipo(String email, String senha,
// TipoUsuario tipo);
// Optional<Usuario> findByEmail(String email);
// //Optional<Usuario> findByEmailAndSenha(String email, String senha);
// }
