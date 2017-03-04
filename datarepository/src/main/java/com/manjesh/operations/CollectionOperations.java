package com.manjesh.operations;

import com.manjesh.common.Employee;
import com.manjesh.common.HibernateUtil;
import com.manjesh.common.JuniorManager;
import com.manjesh.common.Manager;
import com.manjesh.common.SeniorManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
public class CollectionOperations {

    public static void addCollection() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Manager employee = new Manager();
        employee.setName("yogesh");

        List<String> emails = new ArrayList<String>();
        emails.add("emailaddress1@provider1.com");
        emails.add("emailaddress2@provider2.com");
        emails.add("emailaddress3@provider3.com");
        employee.setEmails(emails);

        session.getTransaction().begin();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }

    public static void addSeniorManagerCollection() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        SeniorManager employee = new SeniorManager();
        employee.setName("yogesh");

        Set<String> emails = new HashSet<String>();
        emails.add("emailaddress1@provider1.com");
        emails.add("emailaddress2@provider2.com");
        emails.add("emailaddress3@provider3.com");
        employee.setGmails(emails);

        session.getTransaction().begin();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }

    static public void storeHashmap() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        JuniorManager employee = new JuniorManager();
        employee.setName("yogesh");

        Map<String, String> emails = new HashMap<String, String>();
        emails.put("Business email", "emailaddress1@provider1.com");
        emails.put("Personal email", "emailaddress2@provider2.com");
        employee.setNamedemails(emails);

        session.getTransaction().begin();
        session.save(employee);
        session.getTransaction().commit();

        session.close();
        HibernateUtil.shutdown();

    }

    public static void readCollection() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Manager manager = session.get(Manager.class, 7l);
        System.out.println(manager.toString());

        session.close();
        HibernateUtil.shutdown();
    }
}
