package com.primer_parcial.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

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
    private double price;

    @NotBlank(message = "Please provide the article description")
    @Size(min = 1, max = 150)
    private String description;

    @NotNull
    @Column(nullable = false)
    private int stock;

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

