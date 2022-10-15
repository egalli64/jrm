package com.example.jrm.s15;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "s15.Car")
@Table(name = "CAR")
public class Car {
    @Id
    @Column(name = "CAR_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(optional = true)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", name=" + name + ", employee=" + employee + "]";
    }
}
