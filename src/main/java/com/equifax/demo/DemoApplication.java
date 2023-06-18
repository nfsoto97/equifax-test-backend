package com.equifax.demo;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.equifax.demo.place.model.Place;
import com.equifax.demo.place.repository.PlaceRepository;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private PlaceRepository placeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //         return new WebMvcConfigurer() {
    //                 @Override
    //                 public void addCorsMappings(CorsRegistry registry) {
    //                         registry.addMapping("/**")
    //                                 .allowedOrigins("http://localhost:4200")
    //                                 .allowedMethods("GET", "POST", "PUT", "DELETE");
    //                 }

    //         };
    // }
    
    // @Bean
    public void generateKeyScret() throws NoSuchAlgorithmException{
        SecretKey secretKey = generateSecretKey();
        String secretKeyString = bytesToHex(secretKey.getEncoded());
        System.out.println("Clave secreta generada: " + secretKeyString);
    }

    // public static void main(String[] args) {
    //     SecretKey secretKey = generateSecretKey();
    //     String secretKeyString = bytesToHex(secretKey.getEncoded());
    //     System.out.println("Clave secreta generada: " + secretKeyString);
    // }

    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSHA256");
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar la clave secreta", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

	// Funcion para poblar tabla "Place", para no tener que ejecutar ddl
	// @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Aquí puedes inicializar datos de prueba
            Place place1 = new Place();
            place1.setName("Place 1");
            place1.setLatitude(40.7128);
            place1.setLongitude(-74.0060);
			place1.setAddress("Address");
            placeRepository.save(place1);

            Place place2 = new Place();
            place2.setName("Place 2");
            place2.setLatitude(51.5074);
            place2.setLongitude(-0.1278);
			place2.setAddress("Address");
            placeRepository.save(place2);

            Place place3 = new Place();
            place3.setName("Place 3");
            place3.setLatitude(48.8566);
            place3.setLongitude(2.3522);
			place3.setAddress("Address");
            placeRepository.save(place3);
        };
    }

}
