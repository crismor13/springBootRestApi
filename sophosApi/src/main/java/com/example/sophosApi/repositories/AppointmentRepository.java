package com.example.sophosApi.repositories;

import com.example.sophosApi.models.AppointmentModel;
import com.example.sophosApi.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentModel, Long> {
    public abstract ArrayList<AppointmentModel> findByDate(LocalDate date);
    public abstract ArrayList<AppointmentModel> findByAffiliate_affiliateId(Long affiliateId);

    public abstract ArrayList<AppointmentModel> findByTest_TestId(Long testId);

//    List<WashComment> findByWash_CarWash_Id($Parameter(name="id") int id)
}