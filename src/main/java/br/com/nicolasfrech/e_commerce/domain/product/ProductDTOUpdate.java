package br.com.nicolasfrech.e_commerce.domain.product;

import jakarta.validation.constraints.NotNull;

public record ProductDTOUpdate(
        @NotNull
        Long id,
        String price,
        String quantity) {
}
