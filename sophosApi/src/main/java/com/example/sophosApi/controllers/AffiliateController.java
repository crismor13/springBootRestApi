package com.example.sophosApi.controllers;

import com.example.sophosApi.DTO.AffiliateDTO;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.services.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/affiliates")
public class AffiliateController {
    @Autowired
    AffiliateService affiliateService;

    @GetMapping()
    public ResponseEntity<?> obtenerAffiliates(){
        try{
            ArrayList<AffiliateModel> prueba = affiliateService.obtenerAffiliates();
            return  new ResponseEntity(prueba, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarAffiliate(@RequestBody AffiliateModel affiliate){
        try{
            AffiliateModel prueba = affiliateService.guardarAffiliate(affiliate);
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<?> obtenerAffiliatePorId(@PathVariable("id") Long id){
        if (affiliateService.obtenerPorId(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return  ResponseEntity.ok(affiliateService.obtenerPorId(id));
        }
    }


    @DeleteMapping( path = "/{id}")
    public ResponseEntity eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.affiliateService.eliminarAffiliate(id);
        if (ok){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarAffiliate(@PathVariable long id,@RequestBody AffiliateDTO affiliateDTO) {

        Optional<AffiliateModel> oldAffiliate = affiliateService.obtenerPorId(id);

        if (oldAffiliate.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            oldAffiliate.get().setName(affiliateDTO.getName());
            oldAffiliate.get().setAge(affiliateDTO.getAge());
            oldAffiliate.get().setEmail(affiliateDTO.getEmail());
        }

        try{
            AffiliateModel prueba = affiliateService.guardarAffiliate(oldAffiliate.get());
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

