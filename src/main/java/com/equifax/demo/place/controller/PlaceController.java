package com.equifax.demo.place.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equifax.demo.place.dto.DetailPlaceDTO;
import com.equifax.demo.place.dto.PlaceResultDTO;
import com.equifax.demo.place.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    // @CrossOrigin(origins = "localhost:4200")
    @GetMapping(path = "/find")
    public ResponseEntity<Map<String, Object>> findByLatitudeAndLongitude(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude){

        // Se consume la capa de Service
        List<DetailPlaceDTO> placeDTOList = placeService.findByLatitudeAndLongitude(latitude,longitude);

        PlaceResultDTO placeResultDTO =new PlaceResultDTO(latitude,longitude,placeDTOList);
        // Construir la estructura del resultado
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("resultados", placeResultDTO);
        
        return ResponseEntity.ok(resultado);
    }
    
}
