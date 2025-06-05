package com.example.potatocatalog.controller;

import com.example.potatocatalog.model.Potato;
import com.example.potatocatalog.service.PotatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/potatoes")
public class PotatoController {
    
    private final PotatoService potatoService;
    
    @Autowired
    public PotatoController(PotatoService potatoService) {
        this.potatoService = potatoService;
    }
    
    @GetMapping
    public List<Potato> getAllPotatoes() {
        return potatoService.getAllPotatoes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Potato> getPotatoById(@PathVariable Long id) {
        return potatoService.getPotatoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Potato createPotato(@Valid @RequestBody Potato potato) {
        return potatoService.savePotato(potato);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Potato> updatePotato(@PathVariable Long id, @Valid @RequestBody Potato potato) {
        return potatoService.updatePotato(id, potato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePotato(@PathVariable Long id) {
        if (potatoService.deletePotato(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}