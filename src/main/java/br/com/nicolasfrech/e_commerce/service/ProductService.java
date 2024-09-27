package br.com.nicolasfrech.e_commerce.service;

import br.com.nicolasfrech.e_commerce.domain.product.*;
import br.com.nicolasfrech.e_commerce.domain.user.CartDTO;
import br.com.nicolasfrech.e_commerce.domain.user.User;
import br.com.nicolasfrech.e_commerce.domain.user.UserRepository;
import br.com.nicolasfrech.e_commerce.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void registProduct(ProductDTO dto) {
        var product = productRepository.findByName(dto.name());

        if(product != null) {
            throw new ValidationException("Produto já registrado!");
        }
        else {
            product = new Product(dto);
            productRepository.save(product);
        }
    }

    public List<ProductDTOReturn> listProducts() {
        List<Product> productList = productRepository.findAllByActiveTrue();
        var productDTO = productList.stream().map(ProductDTOReturn::new).toList();

        return productDTO;
    }

    public ProductDTOReturn searchProductByName(String name) {
        var product = productRepository.findByName(name);

        if(product == null) {
            throw new ValidationException("Não há produtos com esse nome: " + name);
        }
        else {
            return new ProductDTOReturn(product);
        }
    }

    public void deleteProduct(Long id) {
        try {
            var product = productRepository.getReferenceById(id);
            product.delete();
        } catch(EntityNotFoundException e) {
            throw new ValidationException("Não há produtos com esse ID: " + id);
        }
    }

    public ProductDTOReturn updateProduct(ProductDTOUpdate dto) {
        try {
            Product product = productRepository.getReferenceById(dto.id());
            product.update(dto);
            return new ProductDTOReturn(product);
        } catch(EntityNotFoundException e) {
            throw new ValidationException("Não há produtos com esse ID: " + dto.id());
        }
    }

    public void addProductToCart(CartDTO dto) {
        try {
            var product = productRepository.getReferenceById(dto.productId());
            var user = userRepository.getReferenceById(dto.userId());
            user.addProduct(product);
            product.addUser(user);
        } catch (EntityNotFoundException e) {
            throw new ValidationException("Não há produtos ou usuários com esse ID!");
        }
    }

}
