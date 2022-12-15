package com.example.sophosApi.implementation;

import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.repositories.AffiliateRepository;
import com.example.sophosApi.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AffiliateImp implements AffiliateService {
    @Autowired
    AffiliateRepository affiliateRepository;

    @Override
    public List<AffiliateModel> obtenerAffiliates(){
        return (List<AffiliateModel>) affiliateRepository.findAll();
    }
    @Override
    public AffiliateModel guardarAffiliate(AffiliateModel affiliate){
        return affiliateRepository.save(affiliate);
    }
    @Override
    public Optional<AffiliateModel> obtenerPorId(Long id){
        return affiliateRepository.findById(id);
    }

    @Override
    public boolean eliminarAffiliate(Long id) {
        try{
            affiliateRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}