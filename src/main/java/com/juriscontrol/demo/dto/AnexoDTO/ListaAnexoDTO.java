package com.juriscontrol.demo.dto.AnexoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaAnexoDTO {
    
    private Long id;
    private String nomeAnexo;
    private String tipoAnexo;
    private byte[] anexo;
    private Long processoId;
}
