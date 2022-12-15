package com.example.sophosApi.service;

import com.example.sophosApi.models.AffiliateModel;

import java.util.List;
import java.util.Optional;

public interface AffiliateService {

    public List<AffiliateModel> obtenerAffiliates();

    public AffiliateModel guardarAffiliate(AffiliateModel affiliate);

    public Optional<AffiliateModel> obtenerPorId(Long id);

    public boolean eliminarAffiliate(Long id);
}
