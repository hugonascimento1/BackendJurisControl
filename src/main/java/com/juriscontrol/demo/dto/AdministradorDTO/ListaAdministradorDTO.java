package com.juriscontrol.demo.dto.AdministradorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaAdministradorDTO {

    private Long id;
    private String nome;
    private String email;
    private String cnpj;
}
