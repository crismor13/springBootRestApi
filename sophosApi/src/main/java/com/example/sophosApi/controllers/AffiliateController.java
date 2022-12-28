package com.example.sophosApi.controllers;

import com.example.sophosApi.DTO.AffiliateDTO;
import com.example.sophosApi.models.AffiliateModel;
import com.example.sophosApi.service.AffiliateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/affiliates")
public class AffiliateController {
    @Autowired
    AffiliateService affiliateService;

    @GetMapping()
    public ResponseEntity<?> obtenerAffiliates(){
        List<AffiliateModel> prueba = affiliateService.obtenerAffiliates();
        if (prueba.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity(prueba, HttpStatus.OK);
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

    @PostMapping()
    public ResponseEntity<?> guardarAffiliate(@RequestBody @Valid AffiliateModel affiliate, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.NOT_FOUND);
        }

        try{
            AffiliateModel prueba = affiliateService.guardarAffiliate(affiliate);
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping( path = "/{id}")
    public ResponseEntity eliminarPorId(@PathVariable("id") Long id){
        boolean ok = affiliateService.eliminarAffiliate(id);
        if (ok){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarAffiliate(@PathVariable long id, @RequestBody @Valid AffiliateDTO affiliateDTO, BindingResult bindingResult) {

        Optional<AffiliateModel> oldAffiliate = affiliateService.obtenerPorId(id);

        if (oldAffiliate.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.NOT_FOUND);
        } else {
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

