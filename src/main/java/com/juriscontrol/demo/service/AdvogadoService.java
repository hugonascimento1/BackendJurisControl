package com.juriscontrol.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.juriscontrol.demo.dto.AdvogadoDTO.AdvogadoResumoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.AtualizarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.CriarAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaAdvogadoDTO;
import com.juriscontrol.demo.dto.AdvogadoDTO.ListaTudoAdvogadoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.ListaAnexoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.ListaMovimentoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
// import com.juriscontrol.demo.dto.CadastroDTO.CadastroCompletoAdvogadoDTO;
// import com.juriscontrol.demo.dto.ProcessoDTO.ListaProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
// import com.juriscontrol.demo.model.Usuario;
// import com.juriscontrol.demo.model.enums.TipoUsuario;
import com.juriscontrol.demo.repository.AdvogadoRepository;

@Service
public class AdvogadoService {

        @Autowired
        private AdvogadoRepository advogadoRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        // cadastrar advogadp
        public Advogado cadastrarAdvogado(CriarAdvogadoDTO dto) {
                Advogado advogado = new Advogado();
                advogado.setNome(dto.getNome());
                advogado.setEmail(dto.getEmail());
                advogado.setSenha(passwordEncoder.encode(dto.getSenha())); // codificar a senha antes de salvar
                advogado.setRegistroOAB(dto.getRegistroOAB());
                return advogadoRepository.save(advogado);
        }

        // atualizar advogado
        public Advogado atualizarAdvogado(Long id, AtualizarAdvogadoDTO dto) throws AdvogadoNotFoundException {
                Advogado advogado = advogadoRepository.findById(id)
                                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

                advogado.setNome(dto.getNome());
                advogado.setEmail(dto.getEmail());
                advogado.setSenha(passwordEncoder.encode(dto.getSenha())); // codificar a senha antes de salvar
                advogado.setRegistroOAB(dto.getRegistroOAB());
                return advogadoRepository.save(advogado);
        }

        // buscar todos advogados com informações resumidas
        public List<ListaAdvogadoDTO> buscarTodosAdvogadosResumo() {
                return advogadoRepository.findAll().stream().map(advogado -> new ListaAdvogadoDTO(
                                advogado.getId(),
                                advogado.getNome(),
                                advogado.getEmail(),
                                advogado.getSenha(),
                                advogado.getRegistroOAB())).collect(Collectors.toList());
        }

        // busca advogado por ID
        public ListaTudoAdvogadoDTO buscarPorIdAdvogado(Long id) throws AdvogadoNotFoundException {
                Advogado advogado = advogadoRepository.findById(id)
                                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

                List<ListaTudoProcessoDTO> processosDTO = advogado.getProcessos().stream().map(processo -> {
                        List<ListaMovimentoDTO> movimentosDTO = processo.getMovimentos().stream()
                                        .map(mov -> new ListaMovimentoDTO(
                                                        mov.getId(),
                                                        mov.getNomeMovimento(),
                                                        mov.getTipo(),
                                                        mov.getData(),
                                                        processo.getId()))
                                        .collect(Collectors.toList());

                        List<ListaAnexoDTO> anexosDTO = processo.getAnexoDocumentos().stream()
                                        .map(anexo -> new ListaAnexoDTO(
                                                        anexo.getId(),
                                                        anexo.getNomeAnexo(),
                                                        anexo.getTipoAnexo(),
                                                        anexo.getAnexo(),
                                                        processo.getId()))
                                        .collect(Collectors.toList());

                        return new ListaTudoProcessoDTO(
                                        processo.getId(),
                                        processo.getNumeroProcesso(),
                                        processo.getVara(),
                                        processo.getClasseTipo(),
                                        processo.getAssuntosTitulo(),
                                        processo.getStatus(),
                                        processo.getNomeAutor(),
                                        processo.getAdvogadoAutor(),
                                        processo.getNomeReu(),
                                        processo.getAdvogadoReu(),
                                        movimentosDTO,
                                        anexosDTO,
                                        advogado.getId());
                }).collect(Collectors.toList());

                return new ListaTudoAdvogadoDTO(
                                advogado.getId(),
                                advogado.getNome(),
                                advogado.getEmail(),
                                advogado.getSenha(),
                                advogado.getRegistroOAB(),
                                processosDTO);
        }

        // busca advogado por ID resumido
        public ListaAdvogadoDTO buscarPorIdAdvogadoResumo(Long id) throws AdvogadoNotFoundException {
                Advogado advogado = advogadoRepository.findById(id)
                                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

                return new ListaAdvogadoDTO(
                                advogado.getId(),
                                advogado.getNome(),
                                advogado.getEmail(),
                                advogado.getSenha(),
                                advogado.getRegistroOAB());
        }

        // busca todos advogados
        public List<ListaTudoAdvogadoDTO> buscarTodosAdvogados() {
                return advogadoRepository.findAll().stream().map(advogado -> {
                        List<ListaTudoProcessoDTO> processosDTO = advogado.getProcessos().stream().map(processo -> {
                                List<ListaMovimentoDTO> movimentosDTO = processo.getMovimentos().stream()
                                                .map(mov -> new ListaMovimentoDTO(
                                                                mov.getId(),
                                                                mov.getNomeMovimento(),
                                                                mov.getTipo(),
                                                                mov.getData(),
                                                                processo.getId()))
                                                .collect(Collectors.toList());

                                List<ListaAnexoDTO> anexosDTO = processo.getAnexoDocumentos().stream()
                                                .map(anexo -> new ListaAnexoDTO(
                                                                anexo.getId(),
                                                                anexo.getNomeAnexo(),
                                                                anexo.getTipoAnexo(),
                                                                anexo.getAnexo(),
                                                                processo.getId()))
                                                .collect(Collectors.toList());

                                return new ListaTudoProcessoDTO(
                                                processo.getId(),
                                                processo.getNumeroProcesso(),
                                                processo.getVara(),
                                                processo.getClasseTipo(),
                                                processo.getAssuntosTitulo(),
                                                processo.getStatus(),
                                                processo.getNomeAutor(),
                                                processo.getAdvogadoAutor(),
                                                processo.getNomeReu(),
                                                processo.getAdvogadoReu(),
                                                movimentosDTO,
                                                anexosDTO,
                                                advogado.getId());
                        }).collect(Collectors.toList());

                        return new ListaTudoAdvogadoDTO(
                                        advogado.getId(),
                                        advogado.getNome(),
                                        advogado.getEmail(),
                                        advogado.getSenha(),
                                        advogado.getRegistroOAB(),
                                        processosDTO);
                }).collect(Collectors.toList());
        }

        // deletar advogado
        public void deletarAdvogado(Long id) throws AdvogadoNotFoundException {
                Advogado advogado = advogadoRepository.findById(id)
                                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));
                advogadoRepository.delete(advogado);
        }

        // Buscar advogado por e-mail
        public Advogado buscarAdvogadoPorEmail(String email) {
                return advogadoRepository.findByEmail(email).orElse(null);
        }
}
