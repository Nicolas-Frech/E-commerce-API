package br.com.nicolasfrech.e_commerce.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank
        String name,

        String description,

        String price,

        @NotNull
        String quantity) {
}
