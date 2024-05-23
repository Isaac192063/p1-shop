package com.primer_parcial.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the category name")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Please provide the category description")
    @Size(min = 1, max = 100)
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Article> articleList = new ArrayList<>();

}
