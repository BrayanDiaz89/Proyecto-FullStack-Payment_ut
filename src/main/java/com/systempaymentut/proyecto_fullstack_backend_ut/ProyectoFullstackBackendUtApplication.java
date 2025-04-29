package com.systempaymentut.proyecto_fullstack_backend_ut;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Estudiante;
import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.PaymentStatus;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.EstudianteRepository;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.PagoRepository;

@SpringBootApplication
public class ProyectoFullstackBackendUtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFullstackBackendUtApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository){
		return args ->{
			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Brayan")
			.apellido("Diaz")
			.codigo("25142")
			.programaId("ISI25142")
			.build());
			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Brayan")
			.apellido("Diaz")
			.codigo("25143")
			.programaId("ISI25143")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Laura")
			.apellido("Martinez")
			.codigo("25144")
			.programaId("ISI25144")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Carlos")
			.apellido("Ramirez")
			.codigo("25145")
			.programaId("ISI25145")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Sofia")
			.apellido("Gomez")
			.codigo("25146")
			.programaId("ISI25146")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Andres")
			.apellido("Lopez")
			.codigo("25147")
			.programaId("ISI25147")
			.build());

			//Obtener tipos de pagos diferentes para cada estudiante
			TypePayment tiposPago[] = TypePayment.values();
			Random random = new Random();
			
			estudianteRepository.findAll().forEach(estudiante -> {
				for (int i = 0; i < 10; i++){
					int index = random.nextInt(tiposPago.length);
					
					//Construir un objeto Pago con valores aleatorios
					Pago pago = Pago.builder()
					.cantidad(1000 + (int) (Math.random() * 20000))
					.type(tiposPago[index])
					.status(PaymentStatus.CREADO)
					.fecha(LocalDate.now())
					.estudiante(estudiante)
					.build();
				
					pagoRepository.save(pago);
				}

			});		
		};


	}

}
