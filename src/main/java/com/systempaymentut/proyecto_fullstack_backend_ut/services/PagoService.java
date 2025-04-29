package com.systempaymentut.proyecto_fullstack_backend_ut.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Estudiante;
import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.PaymentStatus;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.EstudianteRepository;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;

    public Pago savePago(MultipartFile file, double cantidad, TypePayment type, LocalDate date, String codigoEstudiante)
    throws IOException{

        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");

        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString();

        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");
        
        Files.copy(file.getInputStream(), filePath);

        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);

        Pago pago = Pago.builder()
            .type(type)
            .status(PaymentStatus.CREADO)
            .fecha(date)
            .estudiante(estudiante)
            .cantidad(cantidad)
            .file(filePath.toUri().toString())
            .build();

        return pagoRepository.save(pago);
    }    

    public byte[] getArchivoPorId(Long pagoId) throws IOException {
        Pago pago = pagoRepository.findById(pagoId).get();

        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoPorStatus (PaymentStatus status, Long id) {
        Pago pago = pagoRepository.findById(id).get();
        pago.setStatus(status);

        return pagoRepository.save(pago);
    }
}
