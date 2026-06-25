package br.com.senac.api.controllers;

import br.com.senac.api.dtos.CarrosFiltroDto;
import br.com.senac.api.dtos.CarrosRequestDto;
import br.com.senac.api.entidades.Carros;
import br.com.senac.api.services.CarrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carros")
@CrossOrigin
public class CarrosController {
    private CarrosService carrosService;

    public CarrosController(CarrosService carrosService) {
        this.carrosService = carrosService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carros>> listar(CarrosFiltroDto filtro){
        return ResponseEntity.ok(carrosService.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Carros> criar(
            @RequestBody CarrosRequestDto carro){
        try {
            return ResponseEntity
                    .ok(carrosService.criar(carro));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carros> atualizar(
        @PathVariable Long id,
        @RequestBody CarrosRequestDto carro
    ) {
       try {
           return ResponseEntity
                   .ok(carrosService.atualizar(id, carro));
       } catch (RuntimeException e) {
           return ResponseEntity
                   .badRequest()
                   .body(null);
       } catch (Exception e) {
           return ResponseEntity
                   .internalServerError()
                   .body(null);
       }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        try {
            carrosService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Carros> listarById(@PathVariable Long id){
        try {
            return  ResponseEntity.ok(carrosService.listarById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
