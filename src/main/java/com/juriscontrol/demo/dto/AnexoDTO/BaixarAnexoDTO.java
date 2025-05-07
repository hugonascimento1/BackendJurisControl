package com.juriscontrol.demo.dto.AnexoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaixarAnexoDTO {
    
    private Long id;
    private byte[] anexo;
}
