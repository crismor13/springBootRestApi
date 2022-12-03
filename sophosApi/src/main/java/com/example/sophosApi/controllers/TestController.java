package com.example.sophosApi.controllers;

import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.services.TestService;
import com.example.sophosApi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping()
    public ArrayList<TestModel> obtenerTests(){
        return testService.obtenerTests();
    }

    @PostMapping()
    public TestModel guardarTest(@RequestBody TestModel test){
        return this.testService.guardarTest(test);
    }

    @GetMapping( path = "/{id}")
    public Optional<TestModel> obtenerTestPorId(@PathVariable("id") Long id) {
        return this.testService.obtenerPorId(id);
    }

//    @GetMapping("/query")
//    public ArrayList<TestModel> obtenerTestPorPrioridad(@RequestParam("prioridad") Integer prioridad){
//        return this.testService.obtenerPorPrioridad(prioridad);
//    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.testService.eliminarTest(id);
        if (ok){
            return "Se eliminó el test con id " + id;
        }else{
            return "No pudo eliminar el test con id" + id;
        }
    }

}