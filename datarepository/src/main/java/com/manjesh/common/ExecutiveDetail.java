package com.manjesh.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
@Entity
@Table(name = "executive_detail")
public class ExecutiveDetail {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "passportno")
    private String passportNo;

    @OneToOne(
            mappedBy = "executiveDetail"
            , cascade = CascadeType.ALL
    )
    private Executive executive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }


}
