package com.juriscontrol.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AdministradorDTO.AtualizarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.CriarAdministradorDTO;
import com.juriscontrol.demo.dto.AdministradorDTO.ListaTudoAdministradorDTO;
// import com.juriscontrol.demo.dto.CadastroDTO.CadastroCompletoAdministradorDTO;
import com.juriscontrol.demo.exception.AdministradorNotFoundException;
import com.juriscontrol.demo.model.Administrador;
// import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // cadastrar administrador
    public Administrador cadastrarAdministrador(CriarAdministradorDTO dto) {
        Administrador administrador = new Administrador();
        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(passwordEncoder.encode(dto.getSenha()));
        administrador.setCnpj(dto.getCnpj());
        administrador.setTelefone(dto.getTelefone());
        return administradorRepository.save(administrador);
    }

    // atualizar administrador
    public Administrador atualizarAdministrador(Long id, AtualizarAdministradorDTO dto)
            throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));

        administrador.setNome(dto.getNome());
        administrador.setEmail(dto.getEmail());
        administrador.setSenha(passwordEncoder.encode(dto.getSenha()));
        administrador.setCnpj(dto.getCnpj());
        administrador.setTelefone(dto.getTelefone());
        return administradorRepository.save(administrador);
    }

    // buscar administrador por ID
    public ListaTudoAdministradorDTO buscarPorIdAdministrador(Long id) throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));

        return new ListaTudoAdministradorDTO(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getSenha(),
                administrador.getCnpj(),
                administrador.getTelefone());
    }

    // buscar todos administradores
    public List<ListaTudoAdministradorDTO> buscarTodosAdministradores() {
        return administradorRepository.findAll().stream().map(administrador -> new ListaTudoAdministradorDTO(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getSenha(),
                administrador.getCnpj(),
                administrador.getTelefone())).collect(Collectors.toList());
    }

    // deletar administrador
    public void deletarAdministrador(Long id) throws AdministradorNotFoundException {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new AdministradorNotFoundException("Administrador não encontrado."));
        administradorRepository.delete(administrador);
    }

    // Buscar administrador por e-mail
    public Administrador bsucarAdministradorPorEmail(String email) {
        return administradorRepository.findByEmail(email).orElse(null);
    }
}
