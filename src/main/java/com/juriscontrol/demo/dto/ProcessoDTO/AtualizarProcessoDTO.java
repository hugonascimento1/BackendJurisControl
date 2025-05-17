package com.juriscontrol.demo.dto.ProcessoDTO;

import java.util.List;

import com.juriscontrol.demo.model.Anexo;
import com.juriscontrol.demo.model.Movimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtualizarProcessoDTO {

    private Long id;
    private String vara;
    private String classeTipo;
    private String assuntosTitulo;
    private String comarcaUF;
    private String status;
    private String nomeAutor;
    private String telefoneCliente;
    private String advogadoAutor;
    private String nomeReu;
    private String advogadoReu;
    private List<Movimento> movimentos;
    private List<Anexo> anexoDocumentos;
    private Long advogadoId;
}