package com.example.sophosApi.controllers;

import com.example.sophosApi.implementation.AffiliateImp;
import com.example.sophosApi.models.AffiliateModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebMvcTest(AffiliateController.class)
public class AffiliateControllerTest {

    @MockBean
    private AffiliateImp affiliateImp;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void shouldCreateAffiliate() throws Exception {
        AffiliateModel pepito = new AffiliateModel(1L,"Pepito", 23, "pepito@perez.com");

        mockMvc.perform(post("/affiliates").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pepito)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldNotCreateAffiliate() throws Exception {
        AffiliateModel pepito = new AffiliateModel();

        mockMvc.perform(post("/affiliates").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pepito)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnAffiliateById() throws Exception {
        long id = 1L;
        AffiliateModel pepito = new AffiliateModel(1L,"Pepito", 23, "pepito@perez.com");

        when(affiliateImp.obtenerPorId(id)).thenReturn(Optional.of(pepito));
        mockMvc.perform(get("/affiliates/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.affiliateId").value(pepito.getAffiliateId()))
                .andExpect(jsonPath("$.name").value(pepito.getName()))
                .andExpect(jsonPath("$.age").value(pepito.getAge()))
                .andExpect(jsonPath("$.email").value(pepito.getEmail()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundAffiliate() throws Exception {
        long id = 1L;

        when(affiliateImp.obtenerPorId(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/affiliates/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnListOfAffiliates() throws Exception {
        List<AffiliateModel> affiliates = new ArrayList<>(
                Arrays.asList(new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com"),
                        new AffiliateModel(2L, "Fulano", 30, "fulano@detal.com"),
                        new AffiliateModel(3L, "Hola", 25, "hola@mundo.com")));

        when(affiliateImp.obtenerAffiliates()).thenReturn(affiliates);
        mockMvc.perform(get("/affiliates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(affiliates.size()))
                .andDo(print());
    }


    @Test
    void shouldReturnEmptyListOfAffiliates() throws Exception {
        List<AffiliateModel> affiliates = new ArrayList<>();

        when(affiliateImp.obtenerAffiliates()).thenReturn(affiliates);
        mockMvc.perform(get("/affiliates"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldUpdateAffiliate() throws Exception {
        long id = 1L;

        AffiliateModel oldAffiliate = new AffiliateModel(id, "Cristian", 32, "cristian@email.com");
        AffiliateModel newAffiliate = new AffiliateModel(id, "Cristiano", 33, "cristiano@ronaldo.com");

        when(affiliateImp.obtenerPorId(id)).thenReturn(Optional.of(oldAffiliate));
        when(affiliateImp.guardarAffiliate(any(AffiliateModel.class))).thenReturn(newAffiliate);

        mockMvc.perform(put("/affiliates/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAffiliate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newAffiliate.getName()))
                .andExpect(jsonPath("$.age").value(newAffiliate.getAge()))
                .andExpect(jsonPath("$.email").value(newAffiliate.getEmail()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUpdateAffiliate() throws Exception {
        long id = 1L;

        AffiliateModel updatedAffiliate = new AffiliateModel(id, "Updated", 25, "hds@email.com");

        when(affiliateImp.obtenerPorId(id)).thenReturn(Optional.empty());
        when(affiliateImp.guardarAffiliate(any(AffiliateModel.class))).thenReturn(updatedAffiliate);

        mockMvc.perform(put("/affiliates/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAffiliate)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldDeleteAffiliate() throws Exception {
        long id = 1L;
        mockMvc.perform(delete("/affiliates/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }



}
