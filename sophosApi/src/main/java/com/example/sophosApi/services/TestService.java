package com.example.sophosApi.services;

import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.models.UsuarioModel;
import com.example.sophosApi.repositories.TestRepository;
import com.example.sophosApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
    
    public ArrayList<TestModel> obtenerTests(){
        return (ArrayList<TestModel>) testRepository.findAll();
    }

    public TestModel guardarTest(TestModel miTest){
        return testRepository.save(miTest);
    }

    public Optional<TestModel> obtenerPorId(Long id){
        return testRepository.findById(id);
    }


//    public ArrayList<TestModel>  obtenerPorPrioridad(Integer prioridad) {
//        return testRepository.findByPrioridad(prioridad);
//    }

    public boolean eliminarTest(Long id) {
        try{
            testRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}