package com.juriscontrol.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.juriscontrol.demo.dto.AnexoDTO.AtualizarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarAnexoDTO;
import com.juriscontrol.demo.dto.AnexoDTO.CriarVariosAnexosDTO;
import com.juriscontrol.demo.dto.AnexoDTO.ListaAnexoDTO;
import com.juriscontrol.demo.exception.AnexoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.AnexoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    // criar anexo
    public Anexo criarAnexo(CriarAnexoDTO dto) throws ProcessoNotFoundException, IOException {

        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new ProcessoNotFoundException("Processo nao encontrado."));

        MultipartFile arquivo = dto.getAnexo();
        if (arquivo == null || arquivo.isEmpty()) {
            throw new IOException("O arquivo anexado esta vazio.");
        }

        Anexo anexo = new Anexo();
        anexo.setNomeAnexo(dto.getNomeAnexo() != null ? dto.getNomeAnexo() : arquivo.getOriginalFilename());
        anexo.setTipoAnexo(arquivo.getContentType());
        anexo.setAnexo(arquivo.getBytes());
        anexo.setProcesso(processo);

        return anexoRepository.save(anexo);
    }

    // criar vários anexos
    public List<Anexo> criarVariosAnexos(CriarVariosAnexosDTO dtos) throws ProcessoNotFoundException, IOException {

        Processo processo = processoRepository.findById(dtos.getProcessoId())
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        List<Anexo> anexos = new ArrayList<>();
        for (CriarAnexoDTO dto : dtos.getAnexos()) {
            MultipartFile arquivo = dto.getAnexo();

            if (arquivo != null && !arquivo.isEmpty()) {
                Anexo anexo = new Anexo();
                anexo.setNomeAnexo(dto.getNomeAnexo() != null ? dto.getNomeAnexo() : arquivo.getOriginalFilename());
                anexo.setAnexo(arquivo.getBytes());
                anexo.setProcesso(processo);
                anexos.add(anexoRepository.save(anexo));
            }
        }
        return anexos;
    }

    // atualizar anexo
    public Anexo atualizarAnexo(Long id, AtualizarAnexoDTO dto)
            throws AnexoNotFoundException, ProcessoNotFoundException, IOException {

        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));

        Anexo anexo = anexoRepository.findById(id)
                .orElseThrow(() -> new AnexoNotFoundException("Anexo não encontrado."));

        MultipartFile novoArquivo = dto.getAnexo();
        if (novoArquivo == null || novoArquivo.isEmpty()) {
            throw new IOException("O novo arquivo está vazio.");
        }

        anexo.setNomeAnexo(dto.getNomeAnexo() != null ? dto.getNomeAnexo() : novoArquivo.getOriginalFilename());
        anexo.setTipoAnexo(novoArquivo.getContentType());
        anexo.setAnexo(novoArquivo.getBytes());
        anexo.setProcesso(processo);

        return anexoRepository.save(anexo);
    }

    // buscar anexo por ID
    public ListaAnexoDTO buscarAnexoPorId(Long id) throws AnexoNotFoundException {
        Anexo anexo = anexoRepository.findById(id)
                .orElseThrow(() -> new AnexoNotFoundException("Anexo não encontrado."));

        return new ListaAnexoDTO(
                anexo.getId(),
                anexo.getNomeAnexo(),
                anexo.getTipoAnexo(),
                anexo.getAnexo(),
                anexo.getProcesso().getId());
    }

    // buscar todos os anexos
    public List<ListaAnexoDTO> buscarTodosAnexos() {
        return anexoRepository.findAll().stream()
                .map(anexo -> new ListaAnexoDTO(
                        anexo.getId(),
                        anexo.getNomeAnexo(),
                        anexo.getTipoAnexo(),
                        anexo.getAnexo(),
                        anexo.getProcesso().getId()))
                .collect(Collectors.toList());
    }

    // baixar anexo
    public Anexo baixarAnexo(Long id) throws AnexoNotFoundException, IOException {
        Anexo anexo = anexoRepository.findById(id)
                .orElseThrow(() -> new AnexoNotFoundException("Anexo não encontrado."));

        anexo.getTipoAnexo();
        anexo.getNomeAnexo();
        anexo.getAnexo();

        return anexo;
    }

    // deletar anexo
    public void deletarAnexo(Long id) throws AnexoNotFoundException {
        Anexo anexo = anexoRepository.findById(id)
                .orElseThrow(() -> new AnexoNotFoundException("Anexo não encontrado."));

        anexoRepository.delete(anexo);
    }
}