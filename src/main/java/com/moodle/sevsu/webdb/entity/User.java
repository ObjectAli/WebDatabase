package com.moodle.sevsu.webdb.entity;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "secondname")
    private String secondname;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn
    private Department department;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){}

    public User(String surname, String name, String secondname, String position, Department department, String phone, String email) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.position = position;
        this.department = department;
        this.phone = phone;
        this.email = email;
    }
}
