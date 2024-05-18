package com.primer_parcial.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the article name")
    @Size(min = 2, max = 50)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank(message = "Please provide the article description")
    @Size(min = 1, max = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Temporal(TemporalType.DATE)
    private LocalDate created_at;

    private LocalDate uptated_at;

}
