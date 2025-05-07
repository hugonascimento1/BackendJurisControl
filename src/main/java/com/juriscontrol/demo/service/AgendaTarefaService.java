package com.juriscontrol.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juriscontrol.demo.dto.AgendaTarefaDTO.ListaAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.AtualizarAgendaTarefaDTO;
import com.juriscontrol.demo.dto.AgendaTarefaDTO.CriarAgendaTarefaDTO;
import com.juriscontrol.demo.exception.AdvogadoNotFoundException;
import com.juriscontrol.demo.exception.AgendaTarefaNotFoundException;
import com.juriscontrol.demo.model.Advogado;
import com.juriscontrol.demo.model.AgendaTarefa;
import com.juriscontrol.demo.repository.AdvogadoRepository;
import com.juriscontrol.demo.repository.AgendaTarefaRepository;

@Service
public class AgendaTarefaService {
    
    @Autowired
    private AgendaTarefaRepository agendaTarefaRepository;

    @Autowired
    private AdvogadoRepository advogadoRepository;

    //criar tarefa
    public AgendaTarefa criarAgendaTarefa(CriarAgendaTarefaDTO dto) throws AdvogadoNotFoundException {
        
        Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
            .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));
        
        AgendaTarefa novaTarefa = new AgendaTarefa();
        novaTarefa.setTitulo(dto.getTitulo());
        novaTarefa.setDescricao(dto.getDescricao());
        novaTarefa.setData(dto.getData());
        novaTarefa.setAdvogado(advogado);

        return agendaTarefaRepository.save(novaTarefa);
    }

    //atualizar tarefa
    public AgendaTarefa atualizarAgendaTarefa(Long id, AtualizarAgendaTarefaDTO dto) throws AgendaTarefaNotFoundException, AdvogadoNotFoundException {
        
        Advogado advogado = advogadoRepository.findById(dto.getAdvogadoId())
                .orElseThrow(() -> new AdvogadoNotFoundException("Advogado não encontrado."));

        AgendaTarefa agendaTarefa = agendaTarefaRepository.findById(id)
            .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa não encontrada."));
        
        agendaTarefa.setTitulo(dto.getTitulo());        
        agendaTarefa.setDescricao(dto.getDescricao());
        agendaTarefa.setData(dto.getData());
        agendaTarefa.setAdvogado(advogado);
        
        return agendaTarefaRepository.save(agendaTarefa);
    }

    //buscar tarefa por ID
    public ListaAgendaTarefaDTO buscarPorIdAgendaTarefa(Long id) throws AgendaTarefaNotFoundException {
        
        return agendaTarefaRepository.findById(id)
            .map(ListaAgendaTarefaDTO::new)
            .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa não encontrada."));
    }

    /**
    AgendaTarefa agendaTarefa = agendaTarefaRepository.findById(id)
            .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa não encontrada."));

        return new ListaAgendaTarefaDTO(agendaTarefa);
    *
    return new ListaAgendaTarefaDTO(
        agendaTarefa.getId(),
        agendaTarefa.getTitulo(),
        agendaTarefa.getDescricao(),
        agendaTarefa.getData(),
        agendaTarefa.getAdvogado() != null ? agendaTarefa.getAdvogado().getId() : null
    );
    **/

    //buscar todas as tarefas
    public List<ListaAgendaTarefaDTO> buscarTodasAgendaTarefa() {
        
        return agendaTarefaRepository.findAll().stream()
            .map(ListaAgendaTarefaDTO::new)
            .toList();
    }
    
    /**
    public List<ListaAgendaTarefaDTO> buscarTodasAgendaTarefa() {
        return agendaTarefaRepository.findAll().stream()
            .map(agendaTarefa -> new ListaAgendaTarefaDTO(
                agendaTarefa.getId(),
                agendaTarefa.getTitulo(),
                agendaTarefa.getDescricao(),
                agendaTarefa.getData(),
                agendaTarefa.getAdvogado() != null ? agendaTarefa.getAdvogado().getId() : null
            )).toList();
    }
    **/

    //deletar tarefa
    public void deletarAgendaTarefa(Long id) throws AgendaTarefaNotFoundException {
        AgendaTarefa agendaTarefa = agendaTarefaRepository.findById(id)
            .orElseThrow(() -> new AgendaTarefaNotFoundException("Tarefa não encontrada."));
        
        agendaTarefaRepository.delete(agendaTarefa);
    }
}
