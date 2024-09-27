package br.com.nicolasfrech.e_commerce.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username LIKE username")
    User findByName(String username);

    boolean existsByUsername(String username);

    UserDetails findByUsername(String username);
}
