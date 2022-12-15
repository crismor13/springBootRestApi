package com.example.sophosApi.repositories;

import com.example.sophosApi.models.TestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<TestModel, Long> {
}