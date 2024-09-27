package br.com.nicolasfrech.e_commerce.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    Product findByName(String name);

    List<Product> findAllByActiveTrue();

    Boolean existsByName(String name);
}
