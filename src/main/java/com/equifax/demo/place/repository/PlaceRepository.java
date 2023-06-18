package com.equifax.demo.place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equifax.demo.place.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // Aquí puedes agregar métodos personalizados para consultas específicas si es necesario
    List<Place> findByLatitudeAndLongitude(double latitude, double longitude);

}