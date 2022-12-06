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
import java.util.ArrayList;
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

//    @GetMapping()
//    public ArrayList<AppointmentModel> obtenerCita(){
//        return appointmentService.obtenerAppointments();
//    }

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
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return  ResponseEntity.ok(appointmentService.obtenerPorId(id));
        }
    }

    @GetMapping(path = "/filterByAffiliate/{id}")
    public ResponseEntity<?> obtenerCitasAfiliado(@PathVariable("id") Long id){
        if (appointmentService.obtenerPorIdAfiliado(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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


//    @PostMapping()
//    public AppointmentModel guardarCita(@RequestBody AppointmentModel appointment){
//        return this.appointmentService.guardarAppointment(appointment);
//    }

//    @PostMapping()
//    public AppointmentModel guardarCita(@RequestBody AppointmentDTO appointmentDTO) throws Exception {
//        AppointmentModel appointment = new AppointmentModel();
//        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
//        appointment.setDate(appointmentDTO.getDate());
//        appointment.setHour(appointmentDTO.getHour());
//
//        Long affiliateId = appointmentDTO.getAffiliate();
//        Long testId = appointmentDTO.getTest();
//
//        if(affiliateService.obtenerPorId(affiliateId).isPresent()){
//            appointment.setAffiliate(affiliateService.obtenerPorId(affiliateId).get());
//        } else {
//            throw new Exception("No existe el afiliado");
//        }
//
//        if (testService.obtenerPorId(testId).isPresent()){
//            appointment.setTest(testService.obtenerPorId(testId).get());
//        }else {
//            throw new Exception("No existe el test");
//        }
//
//        return this.appointmentService.guardarAppointment(appointment);
//    }

    @PostMapping()
    public ResponseEntity<?> guardarCita(@RequestBody AppointmentDTO appointmentDTO){
        AppointmentModel appointment = new AppointmentModel();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
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

        return  ResponseEntity.ok(appointmentService.guardarAppointment(appointment));

    }

//    @GetMapping( path = "/{id}")
//    public Optional<AppointmentModel> obtenerAppointmentPorId(@PathVariable("id") Long id) {
//        return this.appointmentService.obtenerPorId(id);
//    }

//    @GetMapping
//    public ResponseEntity obtenerAppointmentPorId(@RequestParam Long id){
//        try{
//            AppointmentModel appointment = appointmentService.obtenerPorId(id).get();
//            return ResponseEntity.ok(appointment);
//        } catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }



//    @GetMapping(path = "affiliates/{id}")
//    public ArrayList<AppointmentModel> obtenerAppointmentPorIdAfiliado(@PathVariable("id") Long affiliateId){
//        return this.appointmentService.obtenerPorIdAfiliado(affiliateId);
//    }
//
//    @GetMapping(path = "/tests/{id}")
//    public ArrayList<AppointmentModel> obtenerAppointmentPorIdTest(@PathVariable("id") Long testId){
//        return this.appointmentService.obtenerPorIdTest(testId);
//    }
//    @GetMapping(path = "/{date}")
//    public ArrayList<AppointmentModel> obtenerPorDate(@PathVariable("date") LocalDate fecha){
//        return this.appointmentService.obtenerPorFecha(fecha);
//    }

//    @GetMapping(path = "/{date}")
//    public ResponseEntity obtenerAppointmentPorFecha(@PathVariable("date") LocalDate fecha){
//        try{
//            ArrayList<AppointmentModel> appointment = appointmentService.obtenerPorFecha(fecha);
//            return ResponseEntity.ok(appointment);
//        } catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    @DeleteMapping( path = "/{id}")
    public ResponseEntity eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.appointmentService.eliminarAppointment(id);
        if (ok){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity eliminarAppointmentPorId(@PathVariable("id")Long id){
//        try{
//            appointmentService.eliminarAppointment(id);
//            return ResponseEntity.status(HttpStatus.OK);
//        } catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

}