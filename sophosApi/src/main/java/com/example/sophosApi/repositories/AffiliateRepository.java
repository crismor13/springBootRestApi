package com.example.sophosApi.repositories;

import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AffiliateRepository extends CrudRepository<AffiliateModel, Long> {
//    public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);
}