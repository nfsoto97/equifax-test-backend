package com.equifax.demo.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equifax.demo.config.security.JwtTokenUtil;
import com.equifax.demo.login.dto.AuthRequest;
import com.equifax.demo.login.dto.AuthResponse;
import com.equifax.demo.login.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired 
    AuthenticationManager authManager;
	@Autowired 
    JwtTokenUtil jwtUtil;

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}