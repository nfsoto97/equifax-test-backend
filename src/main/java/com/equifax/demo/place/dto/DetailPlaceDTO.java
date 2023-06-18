package com.equifax.demo.place.dto;

import lombok.Data;

@Data
public class DetailPlaceDTO {
    private Long id;
    private String name;
    private String address;    
    // Constructor vacío (necesario para Spring)
    public DetailPlaceDTO() {
    }
    
    // Constructor con parámetros
    public DetailPlaceDTO(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address=address;
    }
}