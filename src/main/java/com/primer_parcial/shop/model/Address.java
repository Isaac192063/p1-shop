package com.primer_parcial.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the user street")
    private String street;

    @NotBlank(message = "Please provide the user avenue")
    private String avenue;

    @NotBlank(message = "Please provide the user typeDocument")
    private String postalCode;

    @NotBlank(message = "Please provide the user neighborhood")
    private String neighborhood;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

}
