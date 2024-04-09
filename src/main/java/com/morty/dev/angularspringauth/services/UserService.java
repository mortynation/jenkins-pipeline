package com.morty.dev.angularspringauth.services;

import java.nio.CharBuffer;
import java.util.Optional;

import com.morty.dev.angularspringauth.dto.CredentialsDto;
import com.morty.dev.angularspringauth.dto.SignUpDto;
import com.morty.dev.angularspringauth.dto.UserDto;
import com.morty.dev.angularspringauth.exceptions.AppException;
import com.morty.dev.angularspringauth.mappers.UserMapper;
import com.morty.dev.angularspringauth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.morty.dev.angularspringauth.entities.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	public UserDto login(CredentialsDto credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
		
		
		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
			return userMapper.toUserDto(user);
		}
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);

	}

    public UserDto register(SignUpDto signUpDto) {
		Optional<User> oUser = userRepository.findByLogin(signUpDto.login());

		if(oUser.isPresent()) {
			throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
		}

		User user = userMapper.signUpToUser(signUpDto);

		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
		User savedUser = userRepository.save(user);
		return userMapper.toUserDto(savedUser);

    }
}
