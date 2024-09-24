package br.com.nicolasfrech.e_commerce.domain.product;

public record ProductDTOReturn(Long id, String name, String description, String price, String quantity) {

    public ProductDTOReturn(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
}
