package com.juriscontrol.demo.dto.ProcessoDTO;

import java.util.List;

import com.juriscontrol.demo.dto.AnexoDTO.ListaAnexoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.ListaMovimentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListaTudoProcessoDTO {

    private Long id;
    private String numeroProcesso;
    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String status;
    private String nomeAutor;
    private String advogadoAutor;
    private String nomeReu;
    private String advogadoReu;
    private List<ListaMovimentoDTO> movimentos;
    private List<ListaAnexoDTO> anexoDocumentos;
    private Long advogadoId;
}