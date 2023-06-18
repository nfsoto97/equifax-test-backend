package com.equifax.demo.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.equifax.demo.place.model.Place;
import com.equifax.demo.place.repository.PlaceRepository;
import com.equifax.demo.place.service.PlaceService;


@RunWith(MockitoJUnitRunner.class)
public class PlaceTests {

    @Mock
    private PlaceRepository placeRepository;

    
    @InjectMocks
    private PlaceService placeService;

    @BeforeEach
	public void init(){
		MockitoAnnotations.openMocks(this);
	}

    @Test
    public void testSavePlaces() {
        // Datos de prueba
        Place place1 = new Place();
        place1.setName("Place 1");
        place1.setLatitude(40.7128);
        place1.setLongitude(-74.0060);
        place1.setAddress("Address");

        Place place2 = new Place();
        place2.setName("Place 2");
        place2.setLatitude(51.5074);
        place2.setLongitude(-0.1278);
        place2.setAddress("Address");

        Place place3 = new Place();
        place3.setName("Place 3");
        place3.setLatitude(48.8566);
        place3.setLongitude(2.3522);
        place3.setAddress("Address");

        // Simulación del comportamiento del repository
        Mockito.when(placeRepository.save(place1)).thenReturn(place1);
        Mockito.when(placeRepository.save(place2)).thenReturn(place2);
        Mockito.when(placeRepository.save(place3)).thenReturn(place3);

        // Llamada al método a probar
        Place savedPlace1 = placeService.savePlace(place1);
        Place savedPlace2 = placeService.savePlace(place2);
        Place savedPlace3 = placeService.savePlace(place3);

        // Verificaciones
        assertThat(savedPlace1).isNotNull();
        assertThat(savedPlace1.getName()).isEqualTo(place1.getName());
        assertThat(savedPlace1.getLatitude()).isEqualTo(place1.getLatitude());
        assertThat(savedPlace1.getLongitude()).isEqualTo(place1.getLongitude());
        assertThat(savedPlace1.getAddress()).isEqualTo(place1.getAddress());

        assertThat(savedPlace2).isNotNull();
        assertThat(savedPlace2.getName()).isEqualTo(place2.getName());
        assertThat(savedPlace2.getLatitude()).isEqualTo(place2.getLatitude());
        assertThat(savedPlace2.getLongitude()).isEqualTo(place2.getLongitude());
        assertThat(savedPlace2.getAddress()).isEqualTo(place2.getAddress());

        assertThat(savedPlace3).isNotNull();
        assertThat(savedPlace3.getName()).isEqualTo(place3.getName());
        assertThat(savedPlace3.getLatitude()).isEqualTo(place3.getLatitude());
        assertThat(savedPlace3.getLongitude()).isEqualTo(place3.getLongitude());
        assertThat(savedPlace3.getAddress()).isEqualTo(place3.getAddress());
    }
}