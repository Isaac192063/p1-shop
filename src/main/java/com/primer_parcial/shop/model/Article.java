package com.primer_parcial.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the article name")
    @NotNull(message = "Please provide the value article name")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "Please provide the value article price")
    @Positive(message = "Please enter a valid number")
    @Column(nullable = false)
    private double price;

    @NotNull(message = "Please provide the value article description")
    @NotBlank(message = "Please provide the article description")
    @Size(min = 1, max = 150)
    private String description;

    @Positive(message = "Please enter a valid number stock")
    @NotNull(message = "Please provide the value article stock")
    @Column(nullable = false)
    private int stock;

    @NotBlank(message = "Please provide the article brand")
    @NotNull(message = "Please provide the value article brand")
    @Column(nullable = false)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    private boolean available;

    @Column(nullable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate(){
        created_at = LocalDateTime.now();
        available = true;
    }

    @PreUpdate
    protected void onUpdate(){
        updated_at = LocalDateTime.now();
    }
}

