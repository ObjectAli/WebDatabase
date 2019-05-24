package com.moodle.sevsu.webdb.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn
    private Institute institute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Department() {
    }

    public Department(String title, Institute institute) {
        this.title = title;
        this.institute = institute;
    }
}
