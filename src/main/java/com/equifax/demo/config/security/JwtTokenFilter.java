package com.equifax.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.equifax.demo.login.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
				HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getAccessToken(request);

		if (!jwtUtil.validateAccessToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}

	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}

		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();
		return token;
	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(token);

		UsernamePasswordAuthenticationToken 
			authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetails getUserDetails(String token) {
		User userDetails = new User();
		String[] jwtSubject = jwtUtil.getSubject(token).split(",");

		userDetails.setId(Integer.parseInt(jwtSubject[0]));
		userDetails.setEmail(jwtSubject[1]);

		return userDetails;
	}
}





// {
//     @Autowired
//     private JwtTokenUtil jwtTokenUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//         String token = getTokenFromRequest(request);

//         if (token != null && jwtTokenUtil.validateToken(token)) {
//             String username = jwtTokenUtil.getUsernameFromToken(token);
    
//             // Configurar la autenticación en el contexto de seguridad
//             Authentication authentication = new UsernamePasswordAuthenticationToken(username, null);
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }

//         chain.doFilter(request, response);
//     }

//     private String getTokenFromRequest(HttpServletRequest request) {
//         String bearerToken = request.getHeader("Authorization");
//         if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//             return bearerToken.substring(7);
//         }
//         return null;
//     }
// }