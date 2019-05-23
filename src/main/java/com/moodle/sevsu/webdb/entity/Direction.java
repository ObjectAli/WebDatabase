package com.moodle.sevsu.webdb.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "direction")
public class Direction {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @Column(name = "cipher", unique = true)
    private String cipher;

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

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Direction(){}

    public Direction(String cipher, String title) {
        this.cipher = cipher;
        this.title = title;
    }

    public Direction(String cipher) {
        this.cipher = cipher;
    }
}
