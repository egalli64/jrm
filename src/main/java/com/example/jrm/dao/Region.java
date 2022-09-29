package com.example.jrm.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Region {
    @Id
    @Column(name = "REGION_ID")
    @GeneratedValue
    private int id;
    private String name;

    public Region() {
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

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }
}
