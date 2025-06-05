package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juriscontrol.demo.model.Advogado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarAgendaTarefaDTO {
    
    private String titulo;
    private String descricao;
    private LocalDate data;
    private Long advogadoId;
}
