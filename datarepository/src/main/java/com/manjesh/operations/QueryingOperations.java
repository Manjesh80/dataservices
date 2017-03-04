package com.manjesh.operations;

import com.manjesh.common.Category;
import com.manjesh.common.Employee;
import com.manjesh.common.HibernateUtil;
import com.manjesh.common.Product;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/4/2017.
 */
public class QueryingOperations {

    public static void demoAliasQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Employee.class);
        criteria.createAlias("department", "dept");
        criteria.add(Restrictions.eq("dept.name", "account"));
        List list = criteria.list();

        System.out.println(list.toString());

        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoAggregationQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.groupProperty("category"));

        projectionList.add(Projections.alias(Projections.sum("price"), "price"));
        criteria.createAlias("category", "category");
        projectionList.add(Projections.alias(Projections.property("category.name"), "cat_name"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(criteria.ALIAS_TO_ENTITY_MAP);

        List list = criteria.list();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Map map = (Map) iterator.next();
            System.out.println("Category name: " + map.get("cat_name"));
            System.out.println("SUM(price): " + map.get("price"));
        }

        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoDetachedCriteriaQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.setProjection(Projections.max("createdOn"));

        Criteria criteria = session.createCriteria(Product.class);
        criteria.createAlias("category", "cat");
        criteria.add(Subqueries.propertyEq("cat.createdOn", detachedCriteria));
        List<Product> list = criteria.list();
        for (Product product : list) {
            System.out.println("\nProduct id: " + product.getId());
            System.out.println("Product name: " + product.getName());
            System.out.println("Product price: " + product.getPrice());
            System.out.println("Category name: " + product.getCategory().getName());
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoSQLCriteriaQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM product");

        List<Object[]> list = sqlQuery.list();
        for (Object[] object : list) {
            System.out.println("\nId: " + object[0]);
            System.out.println("Name: " + object[1]);
            System.out.println("Price: " + object[2]);
            System.out.println("Category id: " + object[3]);
        }

        session.close();
        HibernateUtil.shutdown();
    }


    public static void demoSQLCriteriaAddScalaQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT id, name, price, category_id FROM product");
        sqlQuery.addScalar("id", new org.hibernate.type.LongType());
        sqlQuery.addScalar("name", new org.hibernate.type.StringType());
        sqlQuery.addScalar("price", new org.hibernate.type.DoubleType());
        sqlQuery.addScalar("category_id", new org.hibernate.type.LongType());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();

        System.out.println(list.toString());

        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoSQLCriteriaEntityMapperQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM category");
        sqlQuery.addEntity(Category.class);

        List<Category> list = sqlQuery.list();
        for (Category category : list) {
            System.out.println("\nCategory id: " + category.getId());
            System.out.println("Category name: " + category.getName());
            //System.out.println("Category capital name: " + category.getCapitalName());
        }

        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoSQLCriteriaFormulaMapperQuery() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Category.class);
        List<Category> list = criteria.list();
        for (Category category : list) {
            System.out.println("\nProduct name: " + category.getName());
            System.out.println("Product capital name: " + category.getCapitalName());
        }

        session.close();
        HibernateUtil.shutdown();
    }

    public static void demoNamedQueries() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.getNamedQuery("getCategoryNameByName");
        query.setString("name", "Furniture");
        List list = query.list();
        System.out.println("Category size: " + list.size());


        session.close();
        HibernateUtil.shutdown();
    }
}
