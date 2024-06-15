package com.primer_parcial.shop.repository;

import com.primer_parcial.shop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndIdNot(String email, Long id);
    Optional<User> findByEmail(String email);
}
