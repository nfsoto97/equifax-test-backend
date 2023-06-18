package com.equifax.demo.place.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlaceResultDTO {
    private double lan_input;
    private double lng_input;
    private List<DetailPlaceDTO> datos;    
    // Constructor vacío (necesario para Spring)
    public PlaceResultDTO() {

    }

    // Constructor con parámetros
    public PlaceResultDTO(double latitude, double longitude, List<DetailPlaceDTO> placeDTOList) {
        this.lan_input = latitude;
        this.lng_input = longitude;
        this.datos = placeDTOList;
    }
    
}
