package br.com.nicolasfrech.e_commerce.domain.product.dto;

import br.com.nicolasfrech.e_commerce.domain.product.Product;

public record ProductDTOReturn(Long id, String name, String description, String price, String quantity) {

    public ProductDTOReturn(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
}
