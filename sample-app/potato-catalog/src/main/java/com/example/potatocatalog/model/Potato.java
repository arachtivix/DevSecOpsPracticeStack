package com.example.potatocatalog.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Potato {
    @NotNull
    private Long id;
    
    @NotBlank
    private String variety;
    
    @NotBlank
    private String description;
    
    @Positive
    private double pricePerKg;
    
    private String cookingType;
    
    public Potato() {}
    
    public Potato(Long id, String variety, String description, double pricePerKg, String cookingType) {
        this.id = id;
        this.variety = variety;
        this.description = description;
        this.pricePerKg = pricePerKg;
        this.cookingType = cookingType;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getVariety() {
        return variety;
    }
    
    public void setVariety(String variety) {
        this.variety = variety;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPricePerKg() {
        return pricePerKg;
    }
    
    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
    
    public String getCookingType() {
        return cookingType;
    }
    
    public void setCookingType(String cookingType) {
        this.cookingType = cookingType;
    }
}