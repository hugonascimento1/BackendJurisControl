package com.juriscontrol.demo.dto.AnexoDTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarAnexoDTO {

    private String nomeAnexo;
    
    private String tipoAnexo;
    
    private MultipartFile anexo;

    private Long processoId;
}
