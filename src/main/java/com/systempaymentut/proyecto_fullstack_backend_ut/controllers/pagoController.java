package com.systempaymentut.proyecto_fullstack_backend_ut.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Estudiante;
import com.systempaymentut.proyecto_fullstack_backend_ut.entities.Pago;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.PaymentStatus;
import com.systempaymentut.proyecto_fullstack_backend_ut.enums.TypePayment;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.EstudianteRepository;
import com.systempaymentut.proyecto_fullstack_backend_ut.repository.PagoRepository;
import com.systempaymentut.proyecto_fullstack_backend_ut.services.PagoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@CrossOrigin("*") //Permite que esta api sea accesible desde cualquier dominio
public class pagoController {

    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private PagoService service;

    //MÉTODO PARA RETORNAR TODOS LOS ESTUDIANTES
    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes(){
        return estudianteRepository.findAll(); //Retornamos a todos los estudiantes desde la base de datos.
    }

    //METODO PARA VER ESTUDIANTE POR CÓDIGO
    @GetMapping("/estudiantes/{codigo}")
    public Estudiante listarEstudiantePorSuCodigo(@PathVariable String codigo){
        return estudianteRepository.findByCodigo(codigo);
    }

    //METODO PARA LISTAR ESTUDIANTES SEGUN EL PROGRAMA ACADÉMICO
    @GetMapping("/estudiantes/programa")
    public List<Estudiante> listarEstudiantesPorPrograma(@RequestParam String programaId){
        return estudianteRepository.findByProgramaId(programaId);
    }

    //METODO PARA LISTAR PAGOS
    @GetMapping("/pagos")
    public List<Pago> listarTodosLosPagos(){
        return pagoRepository.findAll();
    }

    //MÉTODO QUE DEVUELVA UN PAGO EN ESPECÍFICO
    @GetMapping("/pagos/{idPago}")
    public Pago listarPagoPorId(@PathVariable Long idPago){
        return pagoRepository.findById(idPago).get(); //Busca un pago por su id
    }

    //METODO QUE LISTA LOS PAGOS HECHOS POR UN ESTUDIANTE EN ESPECIFICO SEGUN SU CODIGO
    @GetMapping("/estudiantes/{codigoEstudiante}/pagos")
    public List<Pago> listarPagosPorUnEstudianteEspecifico(@PathVariable String codigoEstudiante){
        return pagoRepository.findByEstudianteCodigo(codigoEstudiante);
    }

    //METODO QUE LISTE TODOS LOS PAGOS SEGÚN SU ESTADO CREADO, VALIDADO Y RECHAZADO
    @GetMapping("pagos/status")
    public List<Pago> listarPagosSegunSuStatus(@RequestParam PaymentStatus status){
        return pagoRepository.findByStatus(status);
    }
    
    //METODO QUE LISTE TODOS LOS PAGOS SEGÚN SU TIPO (EFECTIVO, CHEQUE, TRANSFERENCIA O DEPÓSITO)
    @GetMapping("pagos/type")
    public List<Pago> listarPagosSegunSuTipo(@RequestParam TypePayment type){
        return pagoRepository.findByType(type);
    }

    //METODO PARA ACTUALIZAR ESTADO DE UN PAGO
    @PutMapping("pagos/{idPago}/actualizarPago")
    public Pago actualizarStatusDePagoPorSuID(@RequestParam PaymentStatus status, @PathVariable Long idPago){
        return service.actualizarPagoPorStatus(status, idPago); //Llama al metodo actualizarPago de service, y lo implementa.
    }

    //METODO PARA REGISTRAR UN PAGO CON COMPROBANTE (FILE) pdf
    @PostMapping(path = "pagos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pago guardarPago(
                            @RequestParam("file") MultipartFile file, //Archvio adjunto
                            Double cantidad,
                            TypePayment type,
                            LocalDate date,
                            String codigoEstudiante) throws IOException {

            return service.savePago(file, cantidad, type, date, codigoEstudiante); //Guarda el pago en la bd
        }

    //METODO PARA DESCARGAR ARCHIVO DE PAGO
    @GetMapping(value = "/pagoFile/{idPago}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] listarArchivoPorId(@PathVariable Long idPago) throws IOException {
        return service.getArchivoPorId(idPago);
    }
}
    
