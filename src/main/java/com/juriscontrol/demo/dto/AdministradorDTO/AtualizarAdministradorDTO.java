package com.juriscontrol.demo.dto.AdministradorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarAdministradorDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private String telefone;
}
