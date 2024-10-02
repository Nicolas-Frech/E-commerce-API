package br.com.nicolasfrech.e_commerce.controller;

import br.com.nicolasfrech.e_commerce.domain.product.dto.ProductDTO;
import br.com.nicolasfrech.e_commerce.domain.product.dto.ProductDTOUpdate;
import br.com.nicolasfrech.e_commerce.domain.user.dto.CartDTO;
import br.com.nicolasfrech.e_commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity registProduct(@Valid @RequestBody ProductDTO dto) {
        productService.registProduct(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity listProducts() {
        var products = productService.listProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity searchProductByName(@PathVariable String name) {
        var product = productService.searchProductByName(name);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTOUpdate dto) {
        var product = productService.updateProduct(dto);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/carrinho")
    @Transactional
    public ResponseEntity addProductToCart(@RequestBody CartDTO dto) {
        productService.addProductToCart(dto);
        return ResponseEntity.ok().build();
    }


}
