package br.com.nicolasfrech.e_commerce.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    private String quantity;
    private Boolean active;


    public Product(ProductDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.quantity = dto.quantity();
        this.active = true;
    }

    public void update(ProductDTOUpdate dto) {
        if(dto.price() != null) {
            this.price = dto.price();
        }

        if(dto.quantity() != null) {
            this.quantity = dto.quantity();
        }
    }

    public void delete() {
        this.active = false;
    }
}
