package com.example.sophosApi.controllersUnitTests;

import com.example.sophosApi.DTO.TestDTO;
import com.example.sophosApi.controllers.TestController;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestControllerUnitTest {

    @InjectMocks
    TestController testController = new TestController();
    @Mock
    TestService servicioTest;

    @Mock
    BindingResult bindResult;


    @Test
    void shouldReturnTestsListWhenTheyExist(){
        List<TestModel> myTests = new ArrayList<>(
                Arrays.asList(new TestModel(1L, "Psoriasis", "Algo de la piel"),
                new TestModel(2L, "DFdsfkjd", "ljdfs;jkld jds"),
                new TestModel(3L, "hjd;jkfsd", "jkhdjkf jdjldfjkl jkldjj")));

        when(servicioTest.obtenerTests()).thenReturn(myTests);
        var response = testController.obtenerTests();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnTestsListWhenTheyDoNotExist(){
        List<TestModel> myTests = new ArrayList<>();

        when(servicioTest.obtenerTests()).thenReturn(myTests);
        var response = testController.obtenerTests();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldSaveValidTest(){
        TestModel myTest = new TestModel(1L, "Psoriasis", "Algo de la piel");

        when(bindResult.hasErrors()).thenReturn(false);
        var response = testController.guardarTest(myTest, bindResult);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnTestByIdWhenItExists(){
        TestModel myTest = new TestModel(1L, "Psoriasis", "Algo de la piel");

        when(servicioTest.obtenerPorId(1L)).thenReturn(Optional.of(myTest));
        var response = testController.obtenerTestsPorId(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnTestByIdWhenItDoesNotExist(){

        when(servicioTest.obtenerPorId(1L)).thenReturn(Optional.empty());
        var response = testController.obtenerTestsPorId(1L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldDeleteTestByIdWhenItExists(){
        TestModel myTest = new TestModel(1L, "Psoriasis", "Algo de la piel");

        when(servicioTest.eliminarTest(1L)).thenReturn(true);
        var response = testController.eliminarPorId(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void shouldNotDeleteTestByIdWhenItDoesNotExist(){


        when(servicioTest.eliminarTest(1L)).thenReturn(false);
        var response = testController.eliminarPorId(1L);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateTestByIdWhenItExists(){
        TestDTO myTestDTO = new TestDTO();
        TestModel myTest = new TestModel(1L, "Psoriasis", "Algo de la piel");
        when(servicioTest.obtenerPorId(1L)).thenReturn(Optional.of(myTest));
        when(bindResult.hasErrors()).thenReturn(false);
        when(servicioTest.guardarTest(any())).thenReturn(myTest);
        var response = testController.actualizarTest(1L,myTestDTO,bindResult);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldNotUpdateTestByIdWhenItDoesNotExist(){
        TestDTO myTestDTO = new TestDTO();
        when(servicioTest.obtenerPorId(1L)).thenReturn(Optional.empty());
        var response = testController.actualizarTest(1L,myTestDTO,bindResult);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
