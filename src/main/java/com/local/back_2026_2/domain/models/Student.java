package com.local.back_2026_2.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotBlank
    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstNmae;

    @NotBlank
    @Column(
            name = "last_name",
            nullable = false,
            length = 100
    )
    private String lastName;

    @Email
    @NotBlank
    @Column(
            nullable = false,
            unique = true,
            length = 150
    )
    private String email;

    @NotNull
    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    public Student() {
    }

    public Student(Long id, String firstNmae, String lasName, String email, LocalDate birthDate) {
        this.id = id;
        this.firstNmae = firstNmae;
        this.lastName = lasName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getFirstNmae() {
        return firstNmae;
    }

    @JsonIgnore
    public void setFirstNmae(String firstNmae) {
        this.firstNmae = firstNmae;
    }

    public String getFirstName() {
        return firstNmae;
    }

    public void setFirstName(String firstName) {
        this.firstNmae = firstName;
    }

    @JsonIgnore
    public String getLasName() {
        return lastName;
    }

    @JsonIgnore
    public void setLasName(String lasName) {
        this.lastName = lasName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstNmae='" + firstNmae + '\'' +
                ", lasName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
