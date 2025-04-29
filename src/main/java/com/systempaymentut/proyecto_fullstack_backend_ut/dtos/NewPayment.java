package com.systempaymentut.proyecto_fullstack_backend_ut.dtos;

import java.time.LocalDate;

import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Generar getters and setters
@NoArgsConstructor //Instanciar esta clase, sin obtener información de los nuevos pagos, ni generar reporte, ni establecer un nuevo pago
@AllArgsConstructor //Con esta anotación, aseguro que mis atributos puedan inicializarse más de una vez
public class NewPayment{

        private double cantidad;
        private TypePayment type;
        private LocalDate fecha;
        private String codigoEstudiante;
}
