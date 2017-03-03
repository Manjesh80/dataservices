package com.manjesh.common;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */
@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "deptname")
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Employee> deptEmployees;

    @Override
    public String toString() {
        return "Department Name ==> " + getDeptName() + "\r\n" +
                "Employees ==> " + getDeptEmployees().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Employee> getDeptEmployees() {
        return deptEmployees;
    }

    public void setDeptEmployees(Set<Employee> deptEmployees) {
        this.deptEmployees = deptEmployees;
    }
}
