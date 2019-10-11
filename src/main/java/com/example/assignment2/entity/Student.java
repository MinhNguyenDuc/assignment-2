package com.example.assignment2.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @ManyToMany(mappedBy = "studentSet",
                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
                fetch = FetchType.LAZY)
    private Set<AptechClass> aptechClassSet;


    public Student(@NotNull(message = "Name is mandatory") String name, @NotNull(message = "Password is mandatory") String password, @NotNull(message = "Email is mandatory") @Email(message = "Email should be valid") String email, Set<AptechClass> aptechClassSet) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.aptechClassSet = aptechClassSet;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AptechClass> getAptechClassSet() {
        return aptechClassSet;
    }

    public void setAptechClassSet(Set<AptechClass> aptechClassSet) {
        this.aptechClassSet = aptechClassSet;
    }
}
