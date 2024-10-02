package br.com.nicolasfrech.e_commerce.domain.user;

import br.com.nicolasfrech.e_commerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "users")
    private List<Product> products;

    public User(UserDTO dto) {
        this.username = dto.username();
        this.password = dto.password();
        this.active = true;
        this.products = null;
        this.role = dto.role();
    }

    public User(String username, String encodedPwd, UserRole role) {
        this.username = username;
        this.password = encodedPwd;
        this.active = true;
        this.products = null;
        this.role = role;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void delete() {
        this.active = false;
    }

    public void update(UpdateUserDTO dto) {
        if(dto.role() != null) {
            this.role = dto.role();
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
