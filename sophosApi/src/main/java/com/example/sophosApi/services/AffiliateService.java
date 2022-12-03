package com.example.sophosApi.services;

import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.models.UsuarioModel;
import com.example.sophosApi.repositories.AffiliateRepository;
import com.example.sophosApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AffiliateService {
    @Autowired
    AffiliateRepository affiliateRepository;
    
    public ArrayList<AffiliateModel> obtenerAffiliates(){
        return (ArrayList<AffiliateModel>) affiliateRepository.findAll();
    }

    public AffiliateModel guardarAffiliate(AffiliateModel affiliate){
        return affiliateRepository.save(affiliate);
    }

    public Optional<AffiliateModel> obtenerPorId(Long id){
        return affiliateRepository.findById(id);
    }


//    public ArrayList<AffiliateModel>  obtenerPorPrioridad(Integer prioridad) {
//        return affiliateRepository.findByPrioridad(prioridad);
//    }

    public boolean eliminarAffiliate(Long id) {
        try{
            affiliateRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    
}