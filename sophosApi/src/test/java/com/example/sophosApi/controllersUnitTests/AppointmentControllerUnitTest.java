package com.example.sophosApi.controllersUnitTests;

import com.example.sophosApi.DTO.AppointmentDTO;
import com.example.sophosApi.controllersIntegrationTests.AppointmentController;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.service.AffiliateService;
import com.example.sophosApi.service.AppointmentService;
import com.example.sophosApi.service.TestService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerUnitTest {

    @InjectMocks
    AppointmentController appointmentController = new AppointmentController();

    @Mock
    AppointmentService servicioCita;
    @Mock
    TestService servicioTest;
    @Mock
    AffiliateService servicioAfiliado;

    @Mock
    BindingResult bindResult;


    AffiliateModel pepito = new AffiliateModel(1L,"Pepito", 23, "pepito@perez.com");
    TestModel miTest = new TestModel(1L,"Covid","Test Covid");
    TestModel miTest2 = new TestModel(2L,"Ébola","Test ébola");
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(04,20);
    AppointmentModel miAppointment = new AppointmentModel(1L,date,time,miTest,pepito);

    @Test
    void shouldReturnAppointmentsList(){
        List<AppointmentModel> myAppointments = new ArrayList<>();
        AppointmentModel appointment1 = new AppointmentModel();
        AppointmentModel appointment2 = new AppointmentModel();
        AppointmentModel appointment3 = new AppointmentModel();

        myAppointments.add(appointment1);
        myAppointments.add(appointment2);
        myAppointments.add(appointment3);

        when(servicioCita.obtenerAppointments()).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitas();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnAppointmentsList(){
        List<AppointmentModel> myAppointments = new ArrayList<>();
        when(servicioCita.obtenerAppointments()).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitas();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturnAppointmentByExistingId(){
        AppointmentModel myAppointment = new AppointmentModel();
        when(servicioCita.obtenerPorId(1L)).thenReturn(Optional.of(myAppointment));
        var response = appointmentController.obtenerCitasPorId(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnAppointmentByNonExistingId(){
        when(servicioCita.obtenerPorId(1L)).thenReturn(Optional.empty());
        var response = appointmentController.obtenerCitasPorId(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldFilterByExistingAffiliate(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        myAppointments.add(new AppointmentModel());
        when(servicioCita.obtenerPorIdAfiliado(1L)).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorAfiliado(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotFilterByNonExistingAffiliate(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        when(servicioCita.obtenerPorIdAfiliado(1L)).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorAfiliado(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldFilterByExistingDate(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        myAppointments.add(new AppointmentModel());
        when(servicioCita.obtenerPorFecha(any())).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorFecha(LocalDate.now());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotFilterByNonExistingDate(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        when(servicioCita.obtenerPorFecha(any())).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorFecha(LocalDate.now());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldFilterByExistingTest(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        myAppointments.add(new AppointmentModel());
        when(servicioCita.obtenerPorIdTest(any())).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorTest(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotFilterByNonExistingTest(){
        ArrayList<AppointmentModel> myAppointments = new ArrayList<>();
        when(servicioCita.obtenerPorIdTest(any())).thenReturn(myAppointments);
        var response = appointmentController.obtenerCitasPorTest(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldSaveAppointment(){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        when(bindResult.hasErrors()).thenReturn(false);
        when(servicioTest.obtenerPorId(any())).thenReturn(Optional.of(miTest));
        when(servicioAfiliado.obtenerPorId(any())).thenReturn(Optional.of(pepito));
        when(servicioCita.guardarAppointment(any())).thenReturn(miAppointment);

        var response = appointmentController.guardarCita(appointmentDTO,bindResult);

        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }
//    @Test
//    void shouldNotSaveInvalidAppointment(){
//        AppointmentDTO appointmentDTO = new AppointmentDTO();
//        when(bindResult.hasErrors()).thenReturn(true);
//        when(servicioTest.obtenerPorId(any())).thenReturn(Optional.of(miTest));
//        when(servicioAfiliado.obtenerPorId(any())).thenReturn(Optional.of(pepito));
//        when(servicioCita.guardarAppointment(any())).thenReturn(miAppointment);
//
//        var response = appointmentController.guardarCita(appointmentDTO,bindResult);
//
//        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
//
//    }

    @Test
    void shouldUpdateAppointmentWhenIdExists(){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        when(servicioCita.obtenerPorId(any())).thenReturn(Optional.of(miAppointment));
        when(bindResult.hasErrors()).thenReturn(false);
        when(servicioTest.obtenerPorId(any())).thenReturn(Optional.of(miTest));
        when(servicioAfiliado.obtenerPorId(any())).thenReturn(Optional.of(pepito));
        when(servicioCita.guardarAppointment(any())).thenReturn(miAppointment);

        var response = appointmentController.actualizarCita(1L,appointmentDTO,bindResult);

        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    void shouldNotUpdateAppointmentWhenIdDoesNotExist(){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        when(servicioCita.obtenerPorId(any())).thenReturn(Optional.empty());

        var response = appointmentController.actualizarCita(1L,appointmentDTO,bindResult);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void shouldDeleteAppointmentWhenIdExists(){
        when(servicioCita.eliminarAppointment(any())).thenReturn(true);
        var response = appointmentController.eliminarPorId(1L);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void shouldNotDeleteAppointmentWhenIdDoesNotExist(){
        when(servicioCita.eliminarAppointment(any())).thenReturn(false);
        var response = appointmentController.eliminarPorId(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

}