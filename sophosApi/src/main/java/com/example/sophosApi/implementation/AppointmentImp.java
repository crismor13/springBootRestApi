package com.example.sophosApi.implementation;

import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.repositories.AppointmentRepository;
import com.example.sophosApi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentImp implements AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentModel> obtenerAppointments(){
        return (List<AppointmentModel>) appointmentRepository.findAll();
    }
    @Override
    public AppointmentModel guardarAppointment(AppointmentModel appointment){
        return appointmentRepository.save(appointment);
    }
    @Override
    public Optional<AppointmentModel> obtenerPorId(Long id){
        return appointmentRepository.findById(id);
    }
    @Override
    public ArrayList<AppointmentModel>  obtenerPorFecha(LocalDate date) {
        return appointmentRepository.findByDateOrderByAffiliate(date);
    }
    @Override
    public ArrayList<AppointmentModel>  obtenerPorIdAfiliado(Long affiliateId) {
        return appointmentRepository.findByAffiliate_AffiliateId(affiliateId);
    }
    @Override
    public ArrayList<AppointmentModel>  obtenerPorIdTest(Long IdTest) {
        return appointmentRepository.findByTest_TestId(IdTest);
    }

    @Override
    public boolean eliminarAppointment(Long id) {
        try{
            appointmentRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}