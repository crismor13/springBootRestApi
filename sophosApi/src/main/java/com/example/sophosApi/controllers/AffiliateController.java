package com.example.sophosApi.controllers;

import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.services.AffiliateService;
import com.example.sophosApi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/affiliates")
public class AffiliateController {
    @Autowired
    AffiliateService affiliateService;

    @GetMapping()
    public ArrayList<AffiliateModel> obtenerAffiliates(){
        return affiliateService.obtenerAffiliates();
    }

    @PostMapping()
    public AffiliateModel guardarAffiliate(@RequestBody AffiliateModel affiliate){
        return this.affiliateService.guardarAffiliate(affiliate);
    }

    @GetMapping( path = "/{id}")
    public Optional<AffiliateModel> obtenerAffiliatePorId(@PathVariable("id") Long id) {
        return this.affiliateService.obtenerPorId(id);
    }

//    @GetMapping("/query")
//    public ArrayList<AffiliateModel> obtenerAffiliatePorPrioridad(@RequestParam("prioridad") Integer prioridad){
//        return this.affiliateService.obtenerPorPrioridad(prioridad);
//    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.affiliateService.eliminarAffiliate(id);
        if (ok){
            return "Se eliminó el affiliate con id " + id;
        }else{
            return "No pudo eliminar el affiliate con id" + id;
        }
    }

}