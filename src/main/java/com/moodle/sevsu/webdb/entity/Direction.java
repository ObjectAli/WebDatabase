package com.moodle.sevsu.webdb.entity;

import javax.persistence.*;

@Entity
@Table(name = "test", schema = "test", catalog = "")
public class Direction {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "cipher")
    private String cipher;

    @Column(name = "title")
    private String title;

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

    public Direction(String cipher, String title) {
        this.cipher = cipher;
        this.title = title;
    }
}