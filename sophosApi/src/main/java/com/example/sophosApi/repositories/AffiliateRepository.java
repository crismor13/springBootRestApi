package com.example.sophosApi.repositories;

import com.example.sophosApi.models.AffiliateModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateRepository extends CrudRepository<AffiliateModel, Long> {
}