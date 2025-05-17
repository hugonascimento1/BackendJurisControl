package com.juriscontrol.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AnexoDTO.ListaAnexoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.ListaMovimentoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.AtualizarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.CriarProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    // criar processo
    public Processo criarProcesso(CriarProcessoDTO dto) throws AdvogadoNotFoundException {

        Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

        Processo processo = new Processo();
        processo.setNumeroProcesso(dto.getNumeroProcesso());
        processo.setVara(dto.getVara());
        processo.setClasseTipo(dto.getClasseTipo());
        processo.setAssuntosTitulo(dto.getAssuntosTitulo());
        processo.setComarcaUF(dto.getComarcaUF());
        processo.setStatus(dto.getStatus());
        processo.setNomeAutor(dto.getNomeAutor());
        processo.setTelefoneCliente(dto.getTelefoneCliente());
        processo.setAdvogadoAutor(dto.getAdvogadoAutor());
        processo.setNomeReu(dto.getNomeReu());
        processo.setAdvogadoReu(dto.getAdvogadoReu());
        // processo.setMovimentos(dto.getMovimentos());
        // processo.setAnexoDocumentos(dto.getAnexoDocumentos());
        processo.setAdvogado(advogado);

        // movimentos
        // anexos

        return processoRepository.save(processo);
    }

    // atualizar processo
    public Processo atualizarProcesso(Long id, AtualizarProcessoDTO dto)
            throws ProcessoNotFoundException, AdvogadoNotFoundException {

        Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        processo.setVara(dto.getVara());
        processo.setClasseTipo(dto.getClasseTipo());
        processo.setAssuntosTitulo(dto.getAssuntosTitulo());
        processo.setComarcaUF(dto.getComarcaUF());
        processo.setStatus(dto.getStatus());
        processo.setNomeAutor(dto.getNomeAutor());
        processo.setTelefoneCliente(dto.getTelefoneCliente());
        processo.setNomeReu(dto.getNomeReu());
        processo.setAdvogadoAutor(dto.getAdvogadoAutor());
        processo.setAdvogadoReu(dto.getAdvogadoReu());
        // processo.setMovimentos(dto.getMovimentos());
        // processo.setAnexoDocumentos(dto.getAnexoDocumentos());
        processo.setAdvogado(advogado);

        // movimentos
        // anexos

        return processoRepository.save(processo);
    }

    // buscar processo por ID
    public ListaTudoProcessoDTO buscarPorIdProcesso(Long id) throws ProcessoNotFoundException {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        List<ListaMovimentoDTO> movimentos = processo.getMovimentos() != null ? processo.getMovimentos().stream()
                .map(movimento -> new ListaMovimentoDTO(
                        movimento.getId(),
                        movimento.getNomeMovimento(),
                        movimento.getTipo(),
                        movimento.getData(),
                        movimento.getProcesso() != null ? movimento.getProcesso().getId() : null))
                .collect(Collectors.toList()) : Collections.emptyList();

        List<ListaAnexoDTO> anexos = processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos().stream()
                .map(anexo -> new ListaAnexoDTO(
                        anexo.getId(),
                        anexo.getNomeAnexo(),
                        anexo.getTipoAnexo(),
                        anexo.getAnexo(),
                        anexo.getProcesso() != null ? anexo.getProcesso().getId() : null))
                .collect(Collectors.toList()) : Collections.emptyList();

        return new ListaTudoProcessoDTO(
                processo.getId(),
                processo.getNumeroProcesso(),
                processo.getVara(),
                processo.getClasseTipo(),
                processo.getAssuntosTitulo(),
                processo.getComarcaUF(),
                processo.getStatus(),
                processo.getNomeAutor(),
                processo.getTelefoneCliente(),
                processo.getAdvogadoAutor(),
                processo.getNomeReu(),
                processo.getAdvogadoReu(),
                movimentos,
                anexos,
                processo.getAdvogado() != null ? processo.getAdvogado().getId() : null);
    }

    // (possíveis mudanças - verificar utilidade)
    // buscar todos os processos
    public List<ListaTudoProcessoDTO> buscarTodosProcessos() {
        return processoRepository.findAll().stream().map(processo -> {

            List<ListaMovimentoDTO> movimentos = processo.getMovimentos() != null ? processo.getMovimentos().stream()
                    .map(movimento -> new ListaMovimentoDTO(
                            movimento.getId(),
                            movimento.getNomeMovimento(),
                            movimento.getTipo(),
                            movimento.getData(),
                            movimento.getProcesso() != null ? movimento.getProcesso().getId() : null))
                    .collect(Collectors.toList()) : Collections.emptyList();

            List<ListaAnexoDTO> anexos = processo.getAnexoDocumentos() != null ? processo.getAnexoDocumentos().stream()
                    .map(anexo -> new ListaAnexoDTO(
                            anexo.getId(),
                            anexo.getNomeAnexo(),
                            anexo.getTipoAnexo(),
                            anexo.getAnexo(),
                            anexo.getProcesso() != null ? anexo.getProcesso().getId() : null))
                    .collect(Collectors.toList()) : Collections.emptyList();

            return new ListaTudoProcessoDTO(
                    processo.getId(),
                    processo.getNumeroProcesso(),
                    processo.getVara(),
                    processo.getClasseTipo(),
                    processo.getAssuntosTitulo(),
                    processo.getComarcaUF(),
                    processo.getStatus(),
                    processo.getNomeAutor(),
                    processo.getTelefoneCliente(),
                    processo.getAdvogadoAutor(),
                    processo.getNomeReu(),
                    processo.getAdvogadoReu(),
                    movimentos,
                    anexos,
                    processo.getAdvogado() != null ? processo.getAdvogado().getId() : null);
        }).collect(Collectors.toList());
    }

    // buscar processo por número
    public ListaProcessoDTO buscarPorNumeroProcesso(String numeroProcesso) throws ProcessoNotFoundException {
        Processo processo = processoRepository.findByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        return new ListaProcessoDTO(
                processo.getNumeroProcesso(),
                processo.getAssuntosTitulo(),
                processo.getAdvogado() != null ? processo.getAdvogado().getId() : null);
    }

    // deletar processo
    public void deletarProcesso(Long id) throws ProcessoNotFoundException {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        processoRepository.delete(processo);
    }
}
