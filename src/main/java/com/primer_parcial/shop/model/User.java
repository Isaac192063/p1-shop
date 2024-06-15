package com.primer_parcial.shop.model;

import com.primer_parcial.shop.model.enums.Role;
import com.primer_parcial.shop.model.enums.TypeDocument;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Valid
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
