package com.juriscontrol.demo.dto.AdvogadoDTO;

import java.util.List;

// import com.juriscontrol.demo.dto.ProcessoDTO.ListaProcessoDTO;
import com.juriscontrol.demo.dto.ProcessoDTO.ListaTudoProcessoDTO;

// import java.util.List;

// import com.juriscontrol.demo.dto.ProcessoDTO.ListaProcessoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoAdvogadoDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String registroOAB;
    private List<ListaTudoProcessoDTO> processos;
}
