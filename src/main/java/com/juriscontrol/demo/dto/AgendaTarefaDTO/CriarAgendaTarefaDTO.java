package com.juriscontrol.demo.dto.AgendaTarefaDTO;

import java.time.LocalDateTime;

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
    private LocalDateTime data;
    private Long advogadoId;
}
