// package com.juriscontrol.demo.model;

// import com.juriscontrol.demo.model.enums.TipoUsuario;

// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.NonNull;

// @Entity
// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// public class Usuario {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @NonNull
//     private String nome;
    
//     @NonNull
//     private String email;
    
//     @NonNull
//     private String senha;

//     @Enumerated(EnumType.STRING)
//     private TipoUsuario tipo;
// }
