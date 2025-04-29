package com.systempaymentut.proyecto_fullstack_backend_ut.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder //Patrón de diseño builder, construimos objetos bajo este patrón
@Data //Generador de getters and setters
@AllArgsConstructor //Generador de constructores
@NoArgsConstructor //Generador de constructores vacíos
public class Estudiante {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    
    @Column(unique=true)
    private String codigo;

    private String programaId;
    private String foto;

}
