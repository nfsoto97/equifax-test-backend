package com.equifax.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.equifax.demo.config.security.JwtTokenUtil;
import com.equifax.demo.login.dto.AuthRequest;
import com.equifax.demo.login.dto.AuthResponse;
import com.equifax.demo.login.model.User;
import org.springframework.security.core.Authentication;

@Service
public class LoginService {
    @Autowired 
    AuthenticationManager authManager;
	@Autowired 
    JwtTokenUtil jwtUtil;

    public AuthResponse getUserAuthentication(AuthRequest request){

        Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
		User user = (User) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(user);
		AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

        return response;


    } 
    
}
