package com.juriscontrol.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String numeroProcesso;

    @NonNull
    private String vara;

    @NonNull
    private String classeTipo;

    @NonNull
    private String assuntosTitulo;

    @NonNull
    private String comarcaUF;

    @NonNull
    private String status;

    private String nomeAutor;
    private String telefoneCliente;
    private String advogadoAutor;
    private String nomeReu;
    private String advogadoReu;

    @JsonIgnore
    @OneToMany(mappedBy = "processo")
    private List<Movimento> movimentos;

    @JsonIgnore
    @OneToMany(mappedBy = "processo")
    private List<Anexo> anexoDocumentos;

    @ManyToOne
    @JoinColumn(name = "advogado_id")
    private Advogado advogado;
}
