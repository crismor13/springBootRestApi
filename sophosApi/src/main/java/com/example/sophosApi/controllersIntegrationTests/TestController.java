package com.example.sophosApi.controllersIntegrationTests;
import com.example.sophosApi.DTO.TestDTO;
import com.example.sophosApi.models.TestModel;
import com.example.sophosApi.service.TestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> guardarTest(@RequestBody @Valid TestModel test, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity eliminarPorId(@PathVariable("id") Long id){
        boolean ok = testService.eliminarTest(id);
        if (ok){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarTest(@PathVariable long id, @RequestBody @Valid TestDTO testDTO, BindingResult bindingResult) {

        Optional<TestModel> oldTest = testService.obtenerPorId(id);

        if (oldTest.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.NOT_FOUND);
        } else {
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