package com.example.sophosApi.service;

import com.example.sophosApi.models.AppointmentModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    public List<AppointmentModel> obtenerAppointments();
    public AppointmentModel guardarAppointment(AppointmentModel appointment);
    public Optional<AppointmentModel> obtenerPorId(Long id);
    public ArrayList<AppointmentModel>  obtenerPorFecha(LocalDate date);
    public ArrayList<AppointmentModel>  obtenerPorIdAfiliado(Long affiliateId);
    public ArrayList<AppointmentModel>  obtenerPorIdTest(Long IdTest);
    public boolean eliminarAppointment(Long id);

}
