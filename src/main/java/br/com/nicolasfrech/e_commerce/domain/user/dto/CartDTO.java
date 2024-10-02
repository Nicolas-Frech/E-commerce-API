package br.com.nicolasfrech.e_commerce.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CartDTO(
        @NotBlank
        Long productId,
        @NotBlank
        Long userId) {
}
