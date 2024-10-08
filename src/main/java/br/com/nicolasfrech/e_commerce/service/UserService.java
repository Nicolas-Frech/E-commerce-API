package br.com.nicolasfrech.e_commerce.service;

import br.com.nicolasfrech.e_commerce.domain.product.dto.ProductDTOReturn;
import br.com.nicolasfrech.e_commerce.domain.user.*;
import br.com.nicolasfrech.e_commerce.domain.user.dto.UpdateUserDTO;
import br.com.nicolasfrech.e_commerce.domain.user.dto.UserDTO;
import br.com.nicolasfrech.e_commerce.domain.user.dto.UserDTOReturn;
import br.com.nicolasfrech.e_commerce.infra.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTOReturn registUser(UserDTO dto) {
        var existUser = userRepository.existsByUsername(dto.username());
        if(existUser) {
            throw new ValidationException("Usuário já foi registrado!");
        } else {
            String encodedPwd = passwordEncoder.encode(dto.password());
            User user = new User(dto.username(), encodedPwd, dto.role());
            userRepository.save(user);
            return new UserDTOReturn(user);
        }
    }

    public void deleteUser(Long id) {
        try {
            var user = userRepository.getReferenceById(id);
            user.delete();
        } catch (EntityNotFoundException e) {
            throw new ValidationException("Não há um usuário com esse ID!");
        }
    }

    public void updateUser(Long id , UpdateUserDTO dto) {
        try {
            var user = userRepository.getReferenceById(id);
            user.update(dto);
        } catch (EntityNotFoundException e) {
            throw new ValidationException("Não há um usuário com esse ID!");
        }
    }

    public List<ProductDTOReturn> showProducts(Long id) {
        try {
            var user = userRepository.getReferenceById(id);
            var products = user.getProducts();
            if(products.isEmpty()) {
                throw new ValidationException("Não há nenhum produto no carrinho!");
            } else {
                var productsDTO = products.stream().map(ProductDTOReturn::new).toList();
                return productsDTO;
            }
        } catch (EntityNotFoundException e) {
            throw new ValidationException("Não há um usuário com esse nome!");
        }
    }

    public UserDTOReturn detailUser(Long id) {
        try {
            var user = userRepository.getReferenceById(id);
            return new UserDTOReturn(user);
        } catch (EntityNotFoundException e) {
            throw new ValidationException("Não há um usuário com esse ID!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
