package com.equifax.demo.place.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equifax.demo.place.dto.DetailPlaceDTO;
import com.equifax.demo.place.model.Place;
import com.equifax.demo.place.repository.PlaceRepository;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<DetailPlaceDTO> findByLatitudeAndLongitude(double latitude, double longitude) {
        List<Place> placesPage = placeRepository.findByLatitudeAndLongitude(latitude,longitude);
        List<DetailPlaceDTO> detailPlaceDTOList = placesPage.stream()
                .map(place -> {
                    DetailPlaceDTO detailPlaceDTO = new DetailPlaceDTO(place.getId(),place.getName(),place.getAddress());
                    // Mapear otros atributos según sea necesario
                    return detailPlaceDTO;
                })
                .collect(Collectors.toList());
        return detailPlaceDTOList;
    }

    public Place savePlace(Place place){
        return placeRepository.save(place);
    }

}
