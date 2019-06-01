package com.moodle.sevsu.webdb.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "hours")
    private int hours;

    @Column(name = "eee")
    private int eee;

    @Column(name = "readiness")
    private int readiness;

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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getEee() {
        return eee;
    }

    public void setEee(int eee) {
        this.eee = eee;
    }

    public int getReadiness() {
        return readiness;
    }

    public void setReadiness(int readiness) {
        this.readiness = readiness;
    }

    public Course(){}

    public Course(String title, int hours, int eee, int readiness) {
        this.title = title;
        this.hours = hours;
        this.eee = eee;
        this.readiness = readiness;
    }
}
