package com.juriscontrol.demo.dto.AnexoDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarVariosAnexosDTO {
    
    private List<CriarAnexoDTO> anexos;
    private Long processoId;
}
