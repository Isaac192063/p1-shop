package com.primer_parcial.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    //@NotBlank(message = "Please provide the name")
    //@Size(min = 2, max = 50)
    private String name;

    @Column(nullable = false)
    //@NotBlank(message = "Please provide the price")
    private BigDecimal price;

    @Column(nullable = false)
    //@NotBlank(message = "Please provide the description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Temporal(TemporalType.DATE)
    private LocalDate created_at;

    private LocalDate uptated_at;

}
