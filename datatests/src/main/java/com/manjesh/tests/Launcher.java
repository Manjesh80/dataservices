package com.manjesh.tests;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
public class Launcher {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Detail detail = new Detail();
        detail.setCity("AHM");

        Employee employee = new Employee();
        employee.setName("vishal");
        employee.setEmployeeDetail(detail);

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(detail);

        session.save(employee);
        transaction.commit();
        session.close();

    }
}
