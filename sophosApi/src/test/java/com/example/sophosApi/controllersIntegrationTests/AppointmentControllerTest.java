package com.example.sophosApi.controllersIntegrationTests;

import com.example.sophosApi.DTO.AppointmentDTO;
import com.example.sophosApi.implementation.AffiliateImp;
import com.example.sophosApi.implementation.AppointmentImp;
import com.example.sophosApi.implementation.TestImp;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.models.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @MockBean
    private AppointmentImp appointmentImp;
    @MockBean
    private AffiliateImp affiliateImp;
    @MockBean
    private TestImp testImp;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    AffiliateModel pepito = new AffiliateModel(1L,"Pepito", 23, "pepito@perez.com");
    TestModel miTest = new TestModel(1L,"Covid","Test Covid");
    TestModel miTest2 = new TestModel(2L,"Ébola","Test ébola");
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(04,20);
    AppointmentModel miAppointment = new AppointmentModel(1L,date,time,miTest,pepito);


    @Test
    void shouldCreateAppointment() throws Exception {

        AppointmentDTO miAppDTO = new AppointmentDTO();
        miAppDTO.setAppointmentId(1L);
        miAppDTO.setTest(1L);
        miAppDTO.setAffiliate(1L);
        miAppDTO.setDate(date);
        miAppDTO.setHour(time);

        when(affiliateImp.obtenerPorId(any())).thenReturn(Optional.of(pepito));
        when(testImp.obtenerPorId(any())).thenReturn(Optional.of(miTest));
//        doReturn(miAppointment).when(appointmentImp.guardarAppointment(any()));

        mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(miAppDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

//    @Test
//    void shouldCreateAppointment() throws Exception {
//
//        AffiliateModel miAfiliado = new AffiliateModel(1L,"Hola",23,"hola@mundo.com");
////        affiliateImp.guardarAffiliate(miAfiliado);
//        TestModel miTest = new TestModel(2L,"Covid-19","Test covid");
////        testImp.guardarTest(miTest);
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        AppointmentModel miAppointment = new AppointmentModel(1L,date,time,miTest,miAfiliado);
//
////        mockMvc.perform(post("/affiliates").contentType(MediaType.APPLICATION_JSON)
////                .content(objectMapper.writeValueAsString(miAfiliado)));
////
////        mockMvc.perform(post("/tests").contentType(MediaType.APPLICATION_JSON)
////                .content(objectMapper.writeValueAsString(miTest)));
//
//        mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(miAppointment)))
//                .andExpect(status().isCreated())
//                .andDo(print());
//    }

    @Test
    void shouldReturnAppointmentById() throws Exception {


//        AffiliateModel pepito2 = new AffiliateModel(1L,"Pepito", 23, "pepito2@perez.com");
//        TestModel miTest2 = new TestModel(1L,"Covid","Test Covid");
//        LocalDate date2 = LocalDate.now();
//        LocalTime time2 = LocalTime.of(04,20);
//        AppointmentModel miAppointment2 = new AppointmentModel(1L,date2,time2,miTest2,pepito2);

        long id = 1L;
//        Optional<AppointmentModel> miOptional2 = Optional.of(miAppointment2);
        when(appointmentImp.obtenerPorId(id)).thenReturn(Optional.of(miAppointment));
        mockMvc.perform(get("/appointments/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.appointmentId").value(miAppointment.getAppointmentId()))
                .andExpect(jsonPath("$.date").value(miAppointment.getDate().toString()))
                .andExpect(jsonPath("$.hour").value(miAppointment.getHour().toString()))
                .andExpect(jsonPath("$.test").isNotEmpty())
                .andExpect(jsonPath("$.affiliate").isNotEmpty())
                .andDo(print());
    }
//
    @Test
    void shouldReturnNotFoundAppointment() throws Exception {
        long id = 1L;

        when(appointmentImp.obtenerPorId(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/appointments/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnListOfAppointments() throws Exception {
        List<AppointmentModel> appointments = new ArrayList<>(
                Arrays.asList(new AppointmentModel(1L, date,time,miTest,pepito),
                        new AppointmentModel(2L, date,time,miTest,pepito),
                        new AppointmentModel(3L, date,time,miTest,pepito)));

        when(appointmentImp.obtenerAppointments()).thenReturn(appointments);
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(appointments.size()))
                .andDo(print());
    }

    @Test
    void shouldUpdateAppointment() throws Exception {
        long id = 1L;

        AppointmentModel oldAppointment = new AppointmentModel(id, date,time,miTest,pepito);
//        AppointmentModel newAppointment = new AppointmentModel(id, date,time,miTest2,pepito);

        AppointmentDTO newAppointment = new AppointmentDTO();
        newAppointment.setAppointmentId(1L);
        newAppointment.setTest(3L);
        newAppointment.setAffiliate(3L);
        newAppointment.setDate(date);
        newAppointment.setHour(time);

        when(appointmentImp.obtenerPorId(any())).thenReturn(Optional.of(oldAppointment));
        when(affiliateImp.obtenerPorId(any())).thenReturn(Optional.of(pepito));
        when(testImp.obtenerPorId(any())).thenReturn(Optional.of(miTest));
//        when(appointmentImp.guardarAppointment(any(AppointmentModel.class))).thenReturn(newAppointment);

        mockMvc.perform(put("/appointments/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAppointment)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.appointmentId").value(id))
//                .andExpect(jsonPath("$.date").value(date.toString()))
//                .andExpect(jsonPath("$.hour").value(time.toString()))
//                .andExpect(jsonPath("$.test").isNotEmpty())
//                .andExpect(jsonPath("$.affiliate").isNotEmpty())
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUpdateAppointment() throws Exception {
        long id = 1L;
        AppointmentDTO updatedAppointment = new AppointmentDTO();
        updatedAppointment.setAppointmentId(1L);
        updatedAppointment.setTest(3L);
        updatedAppointment.setAffiliate(3L);
        updatedAppointment.setDate(date);
        updatedAppointment.setHour(time);
//        AppointmentModel updatedAppointment = new AppointmentModel(1L,date,time,miTest,pepito);

        when(appointmentImp.obtenerPorId(id)).thenReturn(Optional.empty());
//        when(appointmentImp.guardarAppointment(any(AppointmentModel.class))).thenReturn(updatedAppointment);

        mockMvc.perform(put("/appointments/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnNotFoundUpdateAppointment2() throws Exception {
        long id = 1L;
        AppointmentDTO updatedAppointment = new AppointmentDTO();
        updatedAppointment.setAppointmentId(1L);
        updatedAppointment.setTest(3L);
        updatedAppointment.setAffiliate(3L);
        updatedAppointment.setDate(date);
        updatedAppointment.setHour(time);

//        AppointmentModel updatedAppointment = new AppointmentModel(1L,date,time,miTest,pepito);

        when(appointmentImp.obtenerPorId(id)).thenReturn(Optional.empty());
//        when(appointmentImp.guardarAppointment(any(AppointmentModel.class))).thenReturn(updatedAppointment);

        mockMvc.perform(put("/appointments/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldDeleteAppointment() throws Exception {
        long id = 1L;
        mockMvc.perform(delete("/appointments/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}