package com.equifax.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.equifax.demo.login.dto.AuthRequest;
import com.equifax.demo.login.dto.AuthResponse;
import com.equifax.demo.login.service.LoginService;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			AuthResponse response = loginService.getUserAuthentication(request);
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}