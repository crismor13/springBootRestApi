package com.example.sophosApi.controllersUnitTests;

import com.example.sophosApi.DTO.AffiliateDTO;
import com.example.sophosApi.controllers.AffiliateController;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.service.AffiliateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AffiliateControllerUnitTest {

    @InjectMocks
    AffiliateController affiliateController = new AffiliateController();
    @Mock
    AffiliateService servicioAfiliado;

    @Mock
    BindingResult bindResult;

    @Test
    void shouldReturnAffiliatesList(){
        List<AffiliateModel> myAffiliates = new ArrayList<>();
        myAffiliates.add(new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com"));
        myAffiliates.add(new AffiliateModel(2L, "Fulano", 30, "fulano@detal.com"));
        myAffiliates.add(new AffiliateModel(3L, "Hola", 25, "hola@mundo.com"));

        when(servicioAfiliado.obtenerAffiliates()).thenReturn(myAffiliates);
        var response = affiliateController.obtenerAffiliates();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void shouldNotReturnAffiliatesList(){
        List<AffiliateModel> myAffiliates = new ArrayList<>();
        when(servicioAfiliado.obtenerAffiliates()).thenReturn(myAffiliates);
        var response = affiliateController.obtenerAffiliates();

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldSaveAffiliate(){
        AffiliateModel myAffiliate = new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com");

        when(servicioAfiliado.guardarAffiliate(myAffiliate)).thenReturn(myAffiliate);
        var response = affiliateController.guardarAffiliate(myAffiliate,bindResult);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

//    @Test
//    void shouldNotSaveAffiliate(){
//        AffiliateModel myAffiliate = new AffiliateModel(1L,null,23," ");
//        when(bindResult.hasErrors()).thenReturn(true);
//        when(bindResult.getFieldError().getDefaultMessage()).thenReturn("Name is mandatory");
//        var response = affiliateController.guardarAffiliate(myAffiliate,bindResult);
//
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }

    @Test
    void shouldReturnAffiliateByExistingId(){
        AffiliateModel myAffiliate = new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com");
        when(servicioAfiliado.obtenerPorId(1L)).thenReturn(Optional.of(myAffiliate));
        var response = affiliateController.obtenerAffiliatePorId(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnAffiliateByNonExistingId(){
        when(servicioAfiliado.obtenerPorId(1L)).thenReturn(Optional.empty());
        var response = affiliateController.obtenerAffiliatePorId(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldDeleteAffiliateByIdWhenItExists(){
        AffiliateModel myAffiliate = new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com");
        when(servicioAfiliado.eliminarAffiliate(1L)).thenReturn(true);
        var response = affiliateController.eliminarPorId(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotDeleteAffiliateByIdWhenItDoesNotExist(){
        when(servicioAfiliado.eliminarAffiliate(1L)).thenReturn(false);
        var response = affiliateController.eliminarPorId(1L);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateAffiliateWhenIdExists(){
        AffiliateModel myAffiliate = new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com");
        AffiliateDTO myAffiliateDTO = new AffiliateDTO();
        when(servicioAfiliado.obtenerPorId(1L)).thenReturn(Optional.of(myAffiliate));
        var response = affiliateController.actualizarAffiliate(1L,myAffiliateDTO,bindResult);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void shouldNotUpdateAffiliateWhenIdDoesNotExist(){
        AffiliateModel myAffiliate = new AffiliateModel(1L, "Pepito", 23, "pepito@perez.com");
        AffiliateDTO myAffiliateDTO = new AffiliateDTO();
        when(servicioAfiliado.obtenerPorId(1L)).thenReturn(Optional.empty());
        var response = affiliateController.actualizarAffiliate(1L,myAffiliateDTO,bindResult);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
}