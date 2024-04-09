package com.morty.dev.angularspringauth.dto;

public record SignUpDto(String firstName, String lastName, String login, char[] password) {
}
