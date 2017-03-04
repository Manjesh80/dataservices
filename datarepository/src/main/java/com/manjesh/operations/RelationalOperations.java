package com.manjesh.operations;

import com.manjesh.common.Actor;
import com.manjesh.common.Detail;
import com.manjesh.common.Developer;
import com.manjesh.common.Executive;
import com.manjesh.common.ExecutiveDetail;
import com.manjesh.common.HibernateUtil;
import com.manjesh.common.Movie;
import com.manjesh.common.PassportDetail;
import com.manjesh.common.Person;
import com.manjesh.common.Supervisor;
import com.manjesh.common.Technology;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
public class RelationalOperations {

    public static void oneToOneRelation() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        PassportDetail detail = new PassportDetail();
        detail.setPassportNo("G51546645");

        Person person = new Person();
        person.setName("Vishal");
        person.setPassportDetail(detail);

        Transaction transaction = session.getTransaction();
        transaction.begin();

        session.save(person);
        transaction.commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void oneToOneRelationFromChild() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        PassportDetail detail = new PassportDetail();
        detail.setPassportNo("G51546645");

        PassportDetail passportDetail = session.get(PassportDetail.class, 15l);
        System.out.println(passportDetail.getPerson().getName());

        session.close();
        HibernateUtil.shutdown();
    }

    // TODO: Not working
    public static void addOneToOneRelationWithSeparateTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.getTransaction();
        transaction.begin();

        Detail detail = new Detail();
        detail.setCity("AHM");

        Supervisor supervisor = new Supervisor();
        supervisor.setName("vishal");
        supervisor.setEmployeeDetail(detail);

        session.save(supervisor);
        transaction.commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void addOneToOneRelationWithPrimaryKey() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        ExecutiveDetail detail = new ExecutiveDetail();
        detail.setPassportNo("G44244781");

        Executive executive = new Executive();
        executive.setId(1);
        executive.setName("Gajendra");
        executive.setExecutiveDetail(detail);

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(executive);
        transaction.commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void addOneToManyRelationWithForeignKey() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Movie movie = new Movie();
        movie.setName("Furious 7");

        Actor actor1 = new Actor();
        actor1.setActorName("Vin Diesel");
        actor1.setMovie(movie);

        Actor actor2 = new Actor();
        actor2.setActorName("Paul Walker");
        actor2.setMovie(movie);

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(movie);
        session.save(actor1);
        session.save(actor2);
        transaction.commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void addManyToManyRelationWithForeignKey() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Developer developer1 = new Developer();
        developer1.setName("Vishal");

        Developer developer2 = new Developer();
        developer2.setName("Yogesh");

        Developer developer3 = new Developer();
        developer3.setName("Virendra");

        Technology technology1 = new Technology();
        technology1.setLanguage("Java");
        technology1.setExpertise("Intermediate");

        Technology technology2 = new Technology();
        technology2.setLanguage("Bigdata");
        technology2.setExpertise("Expert");

        Set<Technology> technologies = new HashSet<Technology>();
        technologies.add(technology1);
        technologies.add(technology2);

        developer1.setTechnology(technologies);
        developer2.setTechnology(technologies);
        developer3.setTechnology(technologies);

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(developer1);
        session.save(developer2);
        session.save(developer3);
        transaction.commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void getManyToManyRelation() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Developer.class);
        criteria.add(Restrictions.eq("id", 71L));

        Developer developer = (Developer) criteria.uniqueResult();
        System.out.println(developer.toString());

        Set<Technology> tech = developer.getTechnology();
        for (Technology technology : tech) {
            System.out.println(technology.toString());
        }

        session.close();
        HibernateUtil.shutdown();
    }
}
































