package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.juriscontrol.demo.model.AgendaTarefa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaAgendaTarefaDTO {
    
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private Long advogadoId;

    public ListaAgendaTarefaDTO(AgendaTarefa agenda) {
        this.id = agenda.getId();
        this.titulo = agenda.getTitulo();
        this.descricao = agenda.getDescricao();
        this.data = agenda.getData();
        this.advogadoId = agenda.getAdvogado() != null ? agenda.getAdvogado().getId() : null;
    }
}
