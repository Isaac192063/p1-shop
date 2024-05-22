package com.primer_parcial.shop.repository;

import com.primer_parcial.shop.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findByNameAndIdNot(String name, Long id);
}
