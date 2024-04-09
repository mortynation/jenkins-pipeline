package com.morty.dev.angularspringauth.controllers;

import com.morty.dev.angularspringauth.config.UserAuthProvider;
import com.morty.dev.angularspringauth.dto.SignUpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morty.dev.angularspringauth.dto.CredentialsDto;
import com.morty.dev.angularspringauth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import com.morty.dev.angularspringauth.services.UserService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
		UserDto user = userService.register(signUpDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
	}

}
