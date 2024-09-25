package br.com.nicolasfrech.e_commerce.domain.user;

import br.com.nicolasfrech.e_commerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean active;

    @ManyToMany(mappedBy = "users")
    private List<Product> products;

    public User(UserDTO dto) {
        this.username = dto.username();
        this.password = dto.password();
        this.active = true;
        this.products = null;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void delete() {
        this.active = false;
    }
}
