package com.example.sophosApi.implementation;

import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.repositories.TestRepository;
import com.example.sophosApi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestImp implements TestService {
    @Autowired
    TestRepository testRepository;
    @Override
    public List<TestModel> obtenerTests(){
        return (List<TestModel>) testRepository.findAll();
    }

    @Override
    public TestModel guardarTest(TestModel miTest){
        return testRepository.save(miTest);
    }

    @Override
    public Optional<TestModel> obtenerPorId(Long id){
        return testRepository.findById(id);
    }


    @Override
    public boolean eliminarTest(Long id) {
        try{
            testRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}