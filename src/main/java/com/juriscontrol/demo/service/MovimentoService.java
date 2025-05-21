package com.juriscontrol.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.MovimentoDTO.AtualizarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.CriarMovimentoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.ListaMovimentoDTO;
import com.juriscontrol.demo.exception.MovimentoNotFoundException;
import com.juriscontrol.demo.exception.ProcessoNotFoundException;
import com.juriscontrol.demo.model.Movimento;
import com.juriscontrol.demo.model.Processo;
import com.juriscontrol.demo.repository.MovimentoRepository;
import com.juriscontrol.demo.repository.ProcessoRepository;

@Service
public class MovimentoService {
    
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    //criar movimento
    public Movimento criarMovimento(CriarMovimentoDTO dto) throws ProcessoNotFoundException {
        Processo processo = processoRepository.findById(dto.getProcessoId())    
            .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));
        
        Movimento novoMovimento = new Movimento();
        novoMovimento.setNomeMovimento(dto.getNomeMovimento());
        novoMovimento.setTipo(dto.getTipo());
        novoMovimento.setData(dto.getData()); //LocalDateTime.now()
        novoMovimento.setProcesso(processo);
        
        return movimentoRepository.save(novoMovimento);
    }   

    //atualizar movimento
    public Movimento atualizarMovimento(Long id, AtualizarMovimentoDTO dto) throws ProcessoNotFoundException, MovimentoNotFoundException {
        Processo processo = processoRepository.findById(dto.getProcessoId())    
            .orElseThrow(() -> new ProcessoNotFoundException("Processo não encontrado."));
        
        Movimento movimento = movimentoRepository.findById(id)
            .orElseThrow(() -> new MovimentoNotFoundException("Movimento não encontrado."));
        
        movimento.setNomeMovimento(dto.getNomeMovimento());
        movimento.setTipo(dto.getTipo());
        movimento.setData(dto.getData());
        movimento.setProcesso(processo);
        
        return movimentoRepository.save(movimento);
    }

    //buscar movimento por ID
    public ListaMovimentoDTO buscarPorIdMovimento(Long id) throws MovimentoNotFoundException {
        
        return movimentoRepository.findById(id)
                .map(ListaMovimentoDTO::new)
                .orElseThrow(() -> new MovimentoNotFoundException("Movimento não encontrado."));
    }

    //buscar todos os movimentos
    public List<ListaMovimentoDTO> buscarTodosMovimentos() {
        
        return movimentoRepository.findAll().stream()
            .map(ListaMovimentoDTO::new)
            .toList();
    }

    //deletar movimento
    public void deletarMovimento(Long id) throws MovimentoNotFoundException {
        Movimento movimento = movimentoRepository.findById(id)
            .orElseThrow(() -> new MovimentoNotFoundException("Movimento não encontrado."));        
        
        movimentoRepository.delete(movimento);
    }
}
