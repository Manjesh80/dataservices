package com.manjesh.tests;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name="employee_detail"
            , joinColumns=@JoinColumn(name="employee_id")
            , inverseJoinColumns=@JoinColumn(name="detail_id")
    )
    private Detail employeeDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Detail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(Detail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    @Override
    public String toString() {
        return "Employee"
                +"\n Id: " + this.id
                +"\n Name: " + this.name
                +"\n Employee Detail "
                + "\n\t Id: " + this.employeeDetail.getId()
                + "\n\t City: " + this.employeeDetail.getCity();

    }

}