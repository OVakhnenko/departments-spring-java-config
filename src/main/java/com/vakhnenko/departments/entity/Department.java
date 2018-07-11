package com.vakhnenko.departments.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "department", catalog = "departments")
public class Department implements com.vakhnenko.departments.entity.Entity, java.io.Serializable {

    private int department_id;
    @NotEmpty
    private String name;
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int department_id, String name, Set<Employee> employees) {
        this.department_id = department_id;
        this.name = name;
        this.employees = employees;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "department_id", unique = true, nullable = false)
    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department { name=" + name + " id=" + department_id + '}';
    }
}
