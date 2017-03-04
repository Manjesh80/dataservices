package com.manjesh.common;

import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
@Entity
@Table(name = "juniormanager")
public class JuniorManager {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "namedemail")
    private Map<String, String> namedemails;

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

    public Map<String, String> getNamedemails() {
        return namedemails;
    }

    public void setNamedemails(Map<String, String> namedemails) {
        this.namedemails = namedemails;
    }

    @Override
    public String toString() {
        return "Employee"
                + "\n\tId: " + this.id
                + "\n\tName: " + this.name
                + "\n\tEmails: " + this.namedemails;
    }
}