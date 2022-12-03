package com.example.sophosApi.repositories;

import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TestRepository extends CrudRepository<TestModel, Long> {
//    public abstract ArrayList<TestModel> findByPrioridad(Integer prioridad);
}