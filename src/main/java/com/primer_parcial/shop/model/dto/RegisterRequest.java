package com.primer_parcial.shop.model.dto;

import com.primer_parcial.shop.model.Address;
import com.primer_parcial.shop.model.enums.Role;
import com.primer_parcial.shop.model.enums.TypeDocument;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class RegisterRequest {

    @NotBlank(message = "Please provide the user name")
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 100)
    @NotBlank(message = "Please provide the user lastname")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    private LocalDate fullBirthDay;

    @NotNull(message = "Please provide the user document")
    private String document;

    @NotBlank(message = "please provide the email")
    @NotNull(message = "please provide the email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "please provide a number valid")
    private String phoneNumber;

    @NotNull(message = "please provide the password")
    @Size(min = 8,max = 15)
    private String password;

    private Address address;
}
