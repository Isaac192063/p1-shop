package com.primer_parcial.shop.repository;

import com.primer_parcial.shop.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
