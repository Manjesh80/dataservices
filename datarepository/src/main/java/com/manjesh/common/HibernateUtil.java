package com.manjesh.common;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(JointAccount.class);
            configuration.addAnnotatedClass(Manager.class);
            configuration.addAnnotatedClass(Degree.class);
            configuration.addAnnotatedClass(SeniorManager.class);
            configuration.addAnnotatedClass(JuniorManager.class);
            configuration.addAnnotatedClass(PassportDetail.class);
            configuration.addAnnotatedClass(Person.class);

            configuration.addAnnotatedClass(Detail.class);
            configuration.addAnnotatedClass(Supervisor.class);

            configuration.addAnnotatedClass(ExecutiveDetail.class);
            configuration.addAnnotatedClass(Executive.class);

            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Movie.class);

            configuration.addAnnotatedClass(Developer.class);
            configuration.addAnnotatedClass(Technology.class);

            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Category.class);


            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder = builder.applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.out.println("Session factory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
