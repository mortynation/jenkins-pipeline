package com.morty.dev.angularspringauth.mappers;

import com.morty.dev.angularspringauth.dto.SignUpDto;
import org.mapstruct.Mapper;

import com.morty.dev.angularspringauth.dto.UserDto;
import com.morty.dev.angularspringauth.entities.User;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toUserDto(User user);

	@Mapping(target = "password", ignore = true)
	User signUpToUser(SignUpDto signUpDto);
}
