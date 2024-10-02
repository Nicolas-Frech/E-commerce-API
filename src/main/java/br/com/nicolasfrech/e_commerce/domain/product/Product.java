package br.com.nicolasfrech.e_commerce.domain.product;

import br.com.nicolasfrech.e_commerce.domain.product.dto.ProductDTO;
import br.com.nicolasfrech.e_commerce.domain.product.dto.ProductDTOUpdate;
import br.com.nicolasfrech.e_commerce.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "product_user",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


    public Product(ProductDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.quantity = dto.quantity();
        this.active = true;
        this.users = null;
    }

    public void addUser(User user) {
        this.users.add(user);
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
