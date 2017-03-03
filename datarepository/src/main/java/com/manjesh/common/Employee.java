package com.manjesh.common;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String firstName;

    @Column(columnDefinition = "timestamp NULL DEFAULT CURRENT_TIMESTAMP")
    private String birthDateTime;

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Column(precision = 7, scale = 2)
    private BigDecimal salary;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "\nEmployee"
                + "\n id: " + this.getId()
                + "\n first name: " + this.getFirstName()
                + "\n salary: " + this.getSalary()
                + "\n DOB: " + this.getBirthDateTime()
                + "\n department: " + this.getDepartment().getDeptName();
    }

    public String getBirthDateTime() {
        return birthDateTime;
    }

    public void setBirthDateTime(String birthDateTime) {
        this.birthDateTime = birthDateTime;
    }
}

