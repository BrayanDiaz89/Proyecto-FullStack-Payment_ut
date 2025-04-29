package com.systempaymentut.proyecto_fullstack_backend_ut.entities;

import java.time.LocalDate;

import com.systempaymentut.proyecto_fullstack_backend_ut.enums.PaymentStatus;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private LocalDate fecha;
    private double cantidad;
    private TypePayment type;
    private PaymentStatus status;
    
    private String file;

    //Relación en la base de datos, pueden haber muchos pagos de un estudiante N a 1
    @ManyToOne
    private Estudiante estudiante;


}
