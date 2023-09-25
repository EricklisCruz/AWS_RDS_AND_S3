package com.awsstudies.estudos.dto;

import lombok.Getter;

@Getter
public class AuthenticationDTO {

    private String login;
    private String password;

    public AuthenticationDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
