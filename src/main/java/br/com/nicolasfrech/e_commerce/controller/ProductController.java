package br.com.nicolasfrech.e_commerce.controller;

import br.com.nicolasfrech.e_commerce.domain.product.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registProduct(@Valid @RequestBody ProductDTO dto) {
        Product product = new Product(dto);
        repository.save(product);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity listProducts() {
        List<Product> productList = repository.findAllByActiveTrue();

        var productDTO = productList.stream().map(ProductDTOReturn::new).toList();
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/{name}")
    public ResponseEntity searchProductByName(@PathVariable String name) {
        Product product = repository.findByName(name);

        return ResponseEntity.ok(new ProductDTOReturn(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        Product product = repository.getReferenceById(id);
        product.delete();

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTOUpdate dto) {
        Product product = repository.getReferenceById(dto.id());
        product.update(dto);

        return ResponseEntity.ok(new ProductDTOReturn(product));
    }


}
