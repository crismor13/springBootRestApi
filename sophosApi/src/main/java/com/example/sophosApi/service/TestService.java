package com.example.sophosApi.service;

import com.example.sophosApi.models.TestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TestService {
    public List<TestModel> obtenerTests();

    public TestModel guardarTest(TestModel miTest);

    public Optional<TestModel> obtenerPorId(Long id);

    public boolean eliminarTest(Long id);
}
