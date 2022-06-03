package py.com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.entities.Laptop;
import py.com.repository.LaptopRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LaptopController {
    LaptopRepository laptopRepository;
    private static Logger  log = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(py.com.repository.LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @RequestMapping("/api/laptop")
    public List<Laptop> getAll(){
        return laptopRepository.findAll();

    }
    @RequestMapping(value = "/api/laptop/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneById(@PathVariable Long id ){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        System.out.println(laptop.isEmpty());
        if(laptop.isEmpty()){
            System.out.println("Entrando a BAD_REQUEST");
            return  new ResponseEntity<String>("Laptop no encontrada o no existente",HttpStatus.BAD_REQUEST);
        }
            System.out.println("Entrando a OK");
            return  new ResponseEntity<>(laptop, HttpStatus.OK);

    }
    @PostMapping(value = "/api/laptop/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody  Laptop  laptop ){
        try{
            log.info("trying to create a new laptop");
            laptopRepository.save(laptop);
            return new ResponseEntity(laptop,HttpStatus.OK);

        }catch(Exception e){
            log.error(String.valueOf(e));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping (value = "/api/laptop/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@PathVariable Long id,@RequestBody  Laptop  laptop ){
        try{
            Optional<Laptop> findedLaptop = laptopRepository.findById(id);
            log.info(String.valueOf(findedLaptop.get()));

            if(findedLaptop.isEmpty()){
                log.error("Doesn't exist laptop by id:"+id);
                throw  new Error("Laptop no existe ");
            }
            log.info("trying to update a  laptop with ID:"+id);

            findedLaptop.get().setBrand(laptop.getBrand());
            findedLaptop.get().setModel(laptop.getModel());
            findedLaptop.get().setPrice(laptop.getPrice());
            laptopRepository.save(findedLaptop.get());
            return new ResponseEntity(laptop,HttpStatus.OK);

        }catch(Exception e){
            log.error(String.valueOf(e));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping (value = "/api/laptop/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Long id ){
        Map<String, String> map = new HashMap<>();
        try{
            Optional<Laptop> findedLaptop = laptopRepository.findById(id);
            log.info(String.valueOf(findedLaptop.get()));

            if(findedLaptop.isEmpty()){
                log.error("Doesn't exist laptop by id:"+id);
                throw  new Error("Laptop no existe ");
            }
            log.info("trying to delete a  laptop with ID:"+id);

            laptopRepository.deleteById(id);
            map.put("msg","Ha sido eliminado correctamente");
            return new ResponseEntity(map.get("msg"),HttpStatus.OK);

        }catch(Exception e){
            log.error(String.valueOf(e));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }



}
