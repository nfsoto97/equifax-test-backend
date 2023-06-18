package com.equifax.demo.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.equifax.demo.login.model.User;
import com.equifax.demo.login.repository.UserRepository;
import com.equifax.demo.login.service.UserService;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@RunWith(MockitoJUnitRunner.class)
public class UserTests {

    @InjectMocks
    private UserService userService;

	@Mock
    private UserRepository userRepository;

	@BeforeEach
	public void init(){
		MockitoAnnotations.openMocks(this);
	}

    @Test
    public void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String userEmail="test@equifax.com";
        String encodedPassword = passwordEncoder.encode("12345678");
        User user = new User(userEmail, encodedPassword);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(userEmail);
    }
}
