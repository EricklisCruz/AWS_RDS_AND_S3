package com.awsstudies.estudos.dto;

import com.awsstudies.estudos.model.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserDTO {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String UrlPassword;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.UrlPassword = user.getUrlImage();
    }
}
