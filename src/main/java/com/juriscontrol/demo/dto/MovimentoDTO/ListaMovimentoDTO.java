package com.juriscontrol.demo.dto.MovimentoDTO;

import java.time.LocalDateTime;

import com.juriscontrol.demo.model.Movimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaMovimentoDTO {

    private Long id;
    private String nomeMovimento;
    private String tipo;
    private LocalDateTime data;
    private Long processoId;

    public ListaMovimentoDTO(Movimento movimento) {
        this.id = movimento.getId();
        this.nomeMovimento = movimento.getNomeMovimento();
        this.tipo = movimento.getTipo();
        this.data = movimento.getData();
        this.processoId = movimento.getProcesso() != null ? movimento.getProcesso().getId() : null;
    }
}
