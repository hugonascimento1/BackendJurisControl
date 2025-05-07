package com.juriscontrol.demo.dto.ProcessoDTO;

import java.util.List;

import com.juriscontrol.demo.dto.AnexoDTO.CriarAnexoDTO;
import com.juriscontrol.demo.dto.MovimentoDTO.CriarMovimentoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarProcessoDTO {

    private String numeroProcesso;
    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String status;
    private String nomeAutor;
    private String advogadoAutor;
    private String nomeReu;
    private String advogadoReu;
    private List<CriarMovimentoDTO> movimentos;
    private List<CriarAnexoDTO> anexoDocumentos;
    private Long advogadoId;
}