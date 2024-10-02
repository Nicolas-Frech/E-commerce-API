package br.com.nicolasfrech.e_commerce.domain.user.dto;

import br.com.nicolasfrech.e_commerce.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        UserRole role) {
}
