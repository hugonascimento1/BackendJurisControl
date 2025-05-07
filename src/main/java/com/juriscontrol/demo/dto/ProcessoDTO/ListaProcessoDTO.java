package com.juriscontrol.demo.dto.ProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaProcessoDTO {
    
    private String numeroProcesso;
    private String assuntosTitulo;
    private Long advogadoId;
}