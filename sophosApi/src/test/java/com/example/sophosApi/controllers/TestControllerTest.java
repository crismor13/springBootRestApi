package com.example.sophosApi.controllers;


import com.example.sophosApi.implementation.TestImp;
import com.example.sophosApi.models.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @MockBean
    private TestImp testImp;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    TestModel testCovid = new TestModel(1L, "Covid-19", "Test Covid");
    @Test
    void shouldCreateTest() throws Exception {


        mockMvc.perform(post("/tests").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCovid)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    void shouldReturnTestById() throws Exception {
        long id = 1L;

        when(testImp.obtenerPorId(id)).thenReturn(Optional.of(testCovid));
        mockMvc.perform(get("/tests/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.testId").value(testCovid.getTestId()))
                .andExpect(jsonPath("$.name").value(testCovid.getName()))
                .andExpect(jsonPath("$.description").value(testCovid.getDescription()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundTest() throws Exception {
        long id = 1L;

        when(testImp.obtenerPorId(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/tests/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnListOfTests() throws Exception {
        List<TestModel> tests = new ArrayList<>(
                Arrays.asList(new TestModel(1L, "Psoriasis", "Algo de la piel"),
                        new TestModel(2L, "DFdsfkjd", "ljdfs;jkld jds"),
                        new TestModel(3L, "hjd;jkfsd", "jkhdjkf jdjldfjkl jkldjj")));

        when(testImp.obtenerTests()).thenReturn(tests);
        mockMvc.perform(get("/tests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(tests.size()))
                .andDo(print());
    }
//
    @Test
    void shouldUpdateTest() throws Exception {
        long id = 1L;

        TestModel oldTest = new TestModel(1L, "Psoriasis", "Algo de la piel");
        TestModel newTest = new TestModel(1L, "Covid-19", "Test Covid");

        when(testImp.obtenerPorId(id)).thenReturn(Optional.of(oldTest));
        when(testImp.guardarTest(any(TestModel.class))).thenReturn(newTest);

        mockMvc.perform(put("/tests/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newTest.getName()))
                .andExpect(jsonPath("$.description").value(newTest.getDescription()))
                .andDo(print());
    }
//
    @Test
    void shouldReturnNotFoundUpdateTest() throws Exception {
        long id = 1L;

        TestModel updatedTest = new TestModel(1L, "Covid-19", "Test Covid");

        when(testImp.obtenerPorId(id)).thenReturn(Optional.empty());
        when(testImp.guardarTest(any(TestModel.class))).thenReturn(updatedTest);

        mockMvc.perform(put("/tests/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTest)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
//
    @Test
    void shouldDeleteTest() throws Exception {
        long id = 1L;
        mockMvc.perform(delete("/tests/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


}