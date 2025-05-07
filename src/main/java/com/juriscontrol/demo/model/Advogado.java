package com.juriscontrol.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
// import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
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
public class Advogado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String senha;

    @NonNull
    @Column(unique = true)
    private String registroOAB;

    @JsonIgnore
    @OneToMany(mappedBy = "advogado")
    private List<Processo> processos;

    @JsonIgnore
    @OneToMany(mappedBy = "advogado")
    private List<AgendaTarefa> tarefas;

}
