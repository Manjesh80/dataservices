package com.manjesh.common;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */

@Entity
@IdClass(JointAccount.class)
public class JointAccount implements Serializable {

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    @Column
    private long accountNumber;

    @Id
    private String wifeName;

    @Id
    private String husbandName;

}
