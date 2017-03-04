package com.manjesh.common;

import org.hibernate.annotations.IndexColumn;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
@Entity
@Table(name = "manager")
public class SeniorManager {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gmails")
    protected Set<String> gmails;

    public Set<String> getGmails() {
        return gmails;
    }

    public void setGmails(Set<String> gmails) {
        this.gmails = gmails;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Degree> degrees;

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


    @Override
    public String toString() {
        return "Employee"
                + "\n\tId:" + this.id
                + "\n\tName:" + this.name
                + "\n\tEmails:" + this.getGmails();
    }

}