package br.com.nicolasfrech.e_commerce.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
