package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessosPorAdvogadoDTO {
    private String advogadoNome;
    private Long quantidadeProcessos;
}
