package com.vakhnenko.departments.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "employee", catalog = "departments")
public class Employee implements com.vakhnenko.departments.entity.Entity, java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "employee_id", unique = true, nullable = false)
    private int employee_id;

    @Min(18)
    @Max(99)
    @Column(name = "age", nullable = false, precision = 3, scale = 0)
    private int age;

    @NotEmpty
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "methodology", nullable = false)
    private String methodology;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String type, int age, String language, String methodology, Department department) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.language = language;
        this.methodology = methodology;
        this.department = department;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public boolean hasMethodologyOrLanguageError() {
        boolean developer = type.equals("D");
        boolean hasMethodology = !methodology.equals("");
        boolean manager = type.equals("M");
        boolean hasLanguage = !language.equals("");

        if ((!developer && !manager) ||
                (developer && hasMethodology) ||
                (manager && hasLanguage) ||
                (developer && !hasLanguage) ||
                (manager && !hasMethodology)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Employee { name=" + name + " id=" + employee_id + '}';
    }

    public String typeString() {
        if ("D".equals(type)) {
            return "Developer";
        } else {
            return "Manager";
        }
    }
}
