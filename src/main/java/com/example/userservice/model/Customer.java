package com.example.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Integer id;

    @Column
    @NotBlank(message = "Name is mandatory")
    String name;
    @Column
    @NotBlank(message = "UserName is mandatory")
    String userName;
    @Column
    @NotBlank(message = "Password is mandatory")
    String password;
    @Column
    String address;
    @Column
    String state;
    @Column
    String country;
    @Column(unique = true)
    String email;
    @Column
    @NotBlank(message = "PAN is mandatory")
    String pan;
    @Column
    String contact;
    @Column
    String dob;
    @Column
    AccountType accountType;

    private boolean loggedIn;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime dateCreated;
    @UpdateTimestamp
    LocalDateTime lastModified;

}