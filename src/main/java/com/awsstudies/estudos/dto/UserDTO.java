package com.awsstudies.estudos.dto;

import com.awsstudies.estudos.enums.UserRoles;
import com.awsstudies.estudos.model.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserDTO {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private UserRoles userRoles;
    private String urlImage;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.userRoles = user.getUserRoles();
        this.urlImage = user.getUrlImage();
    }
}
