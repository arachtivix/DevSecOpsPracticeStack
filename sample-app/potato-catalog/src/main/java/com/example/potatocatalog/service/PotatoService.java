package com.example.potatocatalog.service;

import com.example.potatocatalog.model.Potato;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PotatoService {
    private final ConcurrentHashMap<Long, Potato> potatoes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    
    public PotatoService() {
        // Add some sample data
        Potato russet = new Potato(idGenerator.incrementAndGet(), "Russet", "All-purpose potato, great for baking", 2.99, "Baking");
        Potato yukon = new Potato(idGenerator.incrementAndGet(), "Yukon Gold", "Buttery flavor, perfect for mashing", 3.49, "Mashing");
        Potato redPotato = new Potato(idGenerator.incrementAndGet(), "Red Potato", "Waxy texture, holds shape when boiled", 2.79, "Boiling");
        
        potatoes.put(russet.getId(), russet);
        potatoes.put(yukon.getId(), yukon);
        potatoes.put(redPotato.getId(), redPotato);
    }
    
    public List<Potato> getAllPotatoes() {
        return new ArrayList<>(potatoes.values());
    }
    
    public Optional<Potato> getPotatoById(Long id) {
        return Optional.ofNullable(potatoes.get(id));
    }
    
    public Potato savePotato(Potato potato) {
        potato.setId(idGenerator.incrementAndGet());
        potatoes.put(potato.getId(), potato);
        return potato;
    }
    
    public Optional<Potato> updatePotato(Long id, Potato potato) {
        if (potatoes.containsKey(id)) {
            potato.setId(id);
            potatoes.put(id, potato);
            return Optional.of(potato);
        }
        return Optional.empty();
    }
    
    public boolean deletePotato(Long id) {
        return potatoes.remove(id) != null;
    }
}