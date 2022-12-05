package com.example.sophosApi.controllers;

import com.example.sophosApi.DTO.AppointmentCreateDTO;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.models.UsuarioModel;
import com.example.sophosApi.services.AffiliateService;
import com.example.sophosApi.services.AppointmentService;
import com.example.sophosApi.services.AppointmentService;
import com.example.sophosApi.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

//    @Autowired
//    TestService testService;
//
//    @Autowired
//    AffiliateService affiliateService;

    @GetMapping()
    public ArrayList<AppointmentModel> obtenerCita(){
        return appointmentService.obtenerAppointments();
    }

//    @PostMapping()
//    public AppointmentModel guardarCita(@RequestBody AppointmentModel appointment){
//        return this.appointmentService.guardarAppointment(appointment);
//    }

    @PostMapping()
    public AppointmentModel guardarCita(@RequestBody AppointmentCreateDTO appointmentDTO){
        AppointmentModel appointment = new AppointmentModel();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setHour(appointmentDTO.getHour());
        appointment.setAffiliate(new AffiliateModel(appointmentDTO.getAffiliate()));
        appointment.setTest(new TestModel(appointmentDTO.getTest()));
        return this.appointmentService.guardarAppointment(appointment);
    }

    @GetMapping( path = "/{id}")
    public Optional<AppointmentModel> obtenerAppointmentPorId(@PathVariable("id") Long id) {
        return this.appointmentService.obtenerPorId(id);
    }



    @GetMapping(path = "affiliates/{id}")
    public ArrayList<AppointmentModel> obtenerAppointmentPorIdAfiliado(@PathVariable("id") Long affiliateId){
        return this.appointmentService.obtenerPorIdAfiliado(affiliateId);
    }

    @GetMapping(path = "/tests/{id}")
    public ArrayList<AppointmentModel> obtenerAppointmentPorIdTest(@PathVariable("id") Long testId){
        return this.appointmentService.obtenerPorIdTest(testId);
    }
    @GetMapping(path = "/{date}")
    public ArrayList<AppointmentModel> obtenerPorDate(@PathVariable("date") LocalDate fecha){
        return this.appointmentService.obtenerPorFecha(fecha);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.appointmentService.eliminarAppointment(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }

}