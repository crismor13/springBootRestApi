package com.example.sophosApi.controllers;
import com.example.sophosApi.DTO.TestDTO;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping()
    public ResponseEntity<?> obtenerTests(){
        if (testService.obtenerTests().isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return  ResponseEntity.ok(testService.obtenerTests());
        }
    }

    @PostMapping()
    public ResponseEntity<?> guardarTest(@RequestBody TestModel test){
        try{
            TestModel prueba = testService.guardarTest(test);
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<?> obtenerTestsPorId(@PathVariable("id") Long id){
        if (testService.obtenerPorId(id).isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return  ResponseEntity.ok(testService.obtenerPorId(id));
        }
    }


    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.testService.eliminarTest(id);
        if (ok){
            return "Se eliminó el test con id " + id;
        }else{
            return "No pudo eliminar el test con id " + id;
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarTest(@PathVariable long id,@RequestBody TestDTO testDTO) {

        Optional<TestModel> oldTest = testService.obtenerPorId(id);

        if (oldTest.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            oldTest.get().setName(testDTO.getName());
            oldTest.get().setDescription(testDTO.getDescription());
        }

        try{
            TestModel prueba = testService.guardarTest(oldTest.get());
            return  new ResponseEntity(prueba,HttpStatus.CREATED);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}