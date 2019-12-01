package com.javacode.model;

import javax.persistence.*;

@Entity
@Table(name = "users_info")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;

    public User() {

    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

}
