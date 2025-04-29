package com.systempaymentut.proyecto_fullstack_backend_ut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Pago;
import java.util.List;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.PaymentStatus;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    //Lista de pagos asociados a un estudiante
    List<Pago> findByEstudianteCodigo(String codigo);
    //Lista para buscar los pagos por su estado
    List<Pago> findByStatus(PaymentStatus status);

    //Lista de pagos según el tipo seleccionado
    List<Pago> findByType(TypePayment type);
    
}
