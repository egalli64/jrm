package com.example.jrm.s17;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "TEAM_EMPLOYEE", //
            joinColumns = @JoinColumn(name = "TEAM_ID"), //
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private Set<Employee> employees;

    public Team() {
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", employees=" + employees + "]";
    }
}
