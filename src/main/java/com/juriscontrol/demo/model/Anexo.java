package com.juriscontrol.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Anexo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nomeAnexo;

    @NonNull
    private String tipoAnexo;

    @NonNull
    @Lob
    //@Column(columnDefinition = "LONGBLOB")
    private byte[] anexo;

    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;
}
