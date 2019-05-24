package com.moodle.sevsu.webdb.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute {

    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "id", unique =  true, updatable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private List<Direction> directionList;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private List<Department> departmentList;

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

    public List<Direction> getDirectionList() {
        return directionList;
    }

    public void setDirectionList(List<Direction> directionList) {
        this.directionList = directionList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Institute(){}

    public Institute(String title) {
        this.title = title;
    }

}
