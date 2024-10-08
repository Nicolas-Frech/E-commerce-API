package br.com.nicolasfrech.e_commerce.domain.user.dto;

import br.com.nicolasfrech.e_commerce.domain.user.User;

public record UserDTOReturn(Long id, String username, String password, Boolean active) {
    public UserDTOReturn(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getActive());
    }
}
