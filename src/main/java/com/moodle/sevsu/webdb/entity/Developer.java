package com.moodle.sevsu.webdb.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToOne
    @JoinColumn
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Developer(){}

    public Developer(Course course, User user) {
        this.course = course;
        this.user = user;
    }
}
