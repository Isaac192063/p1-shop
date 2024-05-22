package com.primer_parcial.shop.repository;

import com.primer_parcial.shop.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findByName(String name);
    Optional<Article> findByNameAndIdNot(String name, Long id);
}
