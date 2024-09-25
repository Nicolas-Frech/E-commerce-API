package br.com.nicolasfrech.e_commerce.controller;

import br.com.nicolasfrech.e_commerce.domain.product.Product;
import br.com.nicolasfrech.e_commerce.domain.product.ProductDTOReturn;
import br.com.nicolasfrech.e_commerce.domain.user.User;
import br.com.nicolasfrech.e_commerce.domain.user.UserDTO;
import br.com.nicolasfrech.e_commerce.domain.user.UserDTOReturn;
import br.com.nicolasfrech.e_commerce.domain.user.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registUser(@RequestBody @Valid UserDTO dto) {
        User user = new User(dto);
        userRepository.save(user);

        return ResponseEntity.ok(new UserDTOReturn(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        user.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carrinho/{id}")
    public ResponseEntity showProducts(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);

        List<Product> products = user.getProducts();
        var productsDTO = products.stream().map(ProductDTOReturn::new).toList();

        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailUser(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);

        return ResponseEntity.ok(new UserDTOReturn(user));
    }


}