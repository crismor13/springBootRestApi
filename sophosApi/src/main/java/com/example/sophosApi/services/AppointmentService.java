package com.example.sophosApi.services;

import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    
    public ArrayList<AppointmentModel> obtenerAppointments(){
        return (ArrayList<AppointmentModel>) appointmentRepository.findAll();
    }

    public AppointmentModel guardarAppointment(AppointmentModel appointment){
        return appointmentRepository.save(appointment);
    }

    public Optional<AppointmentModel> obtenerPorId(Long id){
        return appointmentRepository.findById(id);
    }

    public ArrayList<AppointmentModel>  obtenerPorFecha(LocalDate date) {
        return appointmentRepository.findByDateOrderByAffiliate(date);
    }
    public ArrayList<AppointmentModel>  obtenerPorIdAfiliado(Long affiliateId) {
        return appointmentRepository.findByAffiliate_AffiliateId(affiliateId);
    }

    public ArrayList<AppointmentModel>  obtenerPorIdTest(Long IdTest) {
        return appointmentRepository.findByTest_TestId(IdTest);
    }
//    public ArrayList<AppointmentModel>  obtenerPorPrioridad(Integer prioridad) {
//        return appointmentRepository.findByPrioridad(prioridad);
//    }

//    public void eliminarAppointment(Long id) {
//        appointmentRepository.deleteById(id);
//    }
    public boolean eliminarAppointment(Long id) {
        try{
            appointmentRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}