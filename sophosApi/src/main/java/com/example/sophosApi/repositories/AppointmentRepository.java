package com.example.sophosApi.repositories;

import com.example.sophosApi.models.AppointmentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentModel, Long> {
    public abstract ArrayList<AppointmentModel> findByDateOrderByAffiliate(LocalDate date);
    public abstract ArrayList<AppointmentModel> findByAffiliate_AffiliateId(Long affiliateId);

    public abstract ArrayList<AppointmentModel> findByTest_TestId(Long testId);

//    List<WashComment> findByWash_CarWash_Id($Parameter(name="id") int id)
}