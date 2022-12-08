package com.example.sophosApi.controllers;

import com.example.sophosApi.DTO.AppointmentDTO;
import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.services.AffiliateService;
import com.example.sophosApi.services.AppointmentService;
import com.example.sophosApi.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    TestService testService;
    @Autowired
    AffiliateService affiliateService;

    @GetMapping()
    public ResponseEntity<?> obtenerCitas(){
        if (appointmentService.obtenerAppointments().isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerAppointments());
        }
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<?> obtenerCitasPorId(@PathVariable("id") Long id){
        if (appointmentService.obtenerPorId(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerPorId(id));
        }
    }

    @GetMapping(path = "/filterByAffiliate/{id}")
    public ResponseEntity<?> obtenerCitasAfiliado(@PathVariable("id") Long id){
        if (appointmentService.obtenerPorIdAfiliado(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerPorIdAfiliado(id));
        }
    }

    @GetMapping(path = "/filterByDate/{date}")
    public ResponseEntity<?> obtenerCitasPorFecha(@PathVariable("date") LocalDate date){
        if (appointmentService.obtenerPorFecha(date).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerPorFecha(date));
        }
    }

    @GetMapping(path = "/filterByTest/{id}")
    public ResponseEntity<?> obtenerCitasPorTest(@PathVariable("id") Long id){
        if (appointmentService.obtenerPorIdTest(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerPorIdTest(id));
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarCita(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentModel appointment = new AppointmentModel();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setHour(appointmentDTO.getHour());

        Long affiliateId = appointmentDTO.getAffiliate();
        Long testId = appointmentDTO.getTest();

        if(affiliateService.obtenerPorId(affiliateId).isPresent()){
            appointment.setAffiliate(affiliateService.obtenerPorId(affiliateId).get());
        } else {
            return  new ResponseEntity("Affiliate does not exist",HttpStatus.NOT_FOUND);
        }

        if (testService.obtenerPorId(testId).isPresent()){
            appointment.setTest(testService.obtenerPorId(testId).get());
        }else {
            return  new ResponseEntity("Test does not exist",HttpStatus.NOT_FOUND);
        }

        try{
            AppointmentModel prueba = appointmentService.guardarAppointment(appointment);
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable long id,@RequestBody AppointmentDTO appointmentDTO) {

        Optional<AppointmentModel> oldAppointment = appointmentService.obtenerPorId(id);

        if (oldAppointment.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            oldAppointment.get().setDate(appointmentDTO.getDate());
            oldAppointment.get().setHour(appointmentDTO.getHour());

            Long affiliateId = appointmentDTO.getAffiliate();
            Long testId = appointmentDTO.getTest();
            if(affiliateService.obtenerPorId(affiliateId).isPresent()){
                oldAppointment.get().setAffiliate(affiliateService.obtenerPorId(affiliateId).get());
            } else {
                return  new ResponseEntity("Affiliate does not exist",HttpStatus.NOT_FOUND);
            }

            if (testService.obtenerPorId(testId).isPresent()){
                oldAppointment.get().setTest(testService.obtenerPorId(testId).get());
            }else {
                return  new ResponseEntity("Test does not exist",HttpStatus.NOT_FOUND);
            }


            try{
                AppointmentModel prueba = appointmentService.guardarAppointment(oldAppointment.get());
                return  new ResponseEntity(prueba,HttpStatus.CREATED);
            } catch (Exception e){
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }


    @DeleteMapping( path = "/{id}")
    public ResponseEntity eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.appointmentService.eliminarAppointment(id);
        if (ok){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}