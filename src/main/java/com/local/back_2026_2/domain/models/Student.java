package com.local.back_2026_2.domain.models;

import java.time.LocalDate;

public class Student {

    private Long id;
    private String firstNmae;
    private String lasName;
    private String email;
    private LocalDate birthDate;

    public Student() {
    }

    public Student(Long id, String firstNmae, String lasName, String email, LocalDate birthDate) {
        this.id = id;
        this.firstNmae = firstNmae;
        this.lasName = lasName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNmae() {
        return firstNmae;
    }

    public void setFirstNmae(String firstNmae) {
        this.firstNmae = firstNmae;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
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
                ", lasName='" + lasName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}


