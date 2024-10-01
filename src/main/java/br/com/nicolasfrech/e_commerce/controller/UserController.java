package br.com.nicolasfrech.e_commerce.controller;

import br.com.nicolasfrech.e_commerce.domain.user.UpdateUserDTO;
import br.com.nicolasfrech.e_commerce.domain.user.User;
import br.com.nicolasfrech.e_commerce.domain.user.UserDTO;

import br.com.nicolasfrech.e_commerce.infra.security.TokenService;
import br.com.nicolasfrech.e_commerce.infra.security.TokenJWTDTO;
import br.com.nicolasfrech.e_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager manager;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity registUser(@RequestBody @Valid UserDTO dto) {
        var user = userService.registUser(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserDTO dto) {
        var authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());


        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carrinho/{id}")
    public ResponseEntity showProducts(@PathVariable Long id) {
        var products = userService.showProducts(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailUser(@PathVariable Long id) {
        var user = userService.detailUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity updateUserRole(UpdateUserDTO dto) {
        userService.updateUserRole(dto);

        return ResponseEntity.ok().build();
    }


}