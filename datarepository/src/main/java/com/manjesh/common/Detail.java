package com.manjesh.common;


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
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue
    @Column(name = "detail_id")
    private long id;

    @Column(name = "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "supervisor_detail"
            , joinColumns = @JoinColumn(name = "detail_id", nullable = true, referencedColumnName = "detail_id")
            , inverseJoinColumns = @JoinColumn(name = "supervisor_id", nullable = true, referencedColumnName = "supervisor_id")
    )
    private Supervisor supervisor;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee Detail"
                + "\n Id: " + this.id
                + "\n City: " + this.city
                + "\n Employee "
                + "\n\t Id: " + this.supervisor.getId()
                + "\n\t Name: " + this.supervisor.getName();

    }

    /*public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }*/
}