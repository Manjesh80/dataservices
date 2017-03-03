package com.manjesh.operations;

import com.manjesh.common.Department;
import com.manjesh.common.Employee;
import com.manjesh.common.EmployeeDetail;
import com.manjesh.common.HibernateUtil;
import com.manjesh.common.JointAccount;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */
public class BasicOperation {

    public static void simpleInsert() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // begin a transaction
        session.getTransaction().begin();

        //creating a department object
        Department department = new Department();
        department.setDeptName("development");

        // save department object
        session.save(department);
        System.out.println("Department saved, id:  " + department.getId());

        //creating an employee object
        Employee employee = new Employee();
        employee.setFirstName("yogesh");
        employee.setSalary(new BigDecimal(50000.00));
        //  set department of employee
        employee.setDepartment(department);

        // save employee object
        session.save(employee);
        System.out.println("Employee saved, id:  " + employee.getId());

        // commit transaction
        session.getTransaction().commit();

        session.close();
        HibernateUtil.shutdown();
    }

    public static void simpleRead() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee emp = session.get(Employee.class, 2l);
        System.out.println(emp.getFirstName());
        session.close();
        HibernateUtil.shutdown();

    }

    public static void readDepartment() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Department dept = session.get(Department.class, 1l);
        System.out.println(dept.toString());
        session.close();
        HibernateUtil.shutdown();
    }

    public static void simpleUpdate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee emp = session.get(Employee.class, 2l);
        System.out.println(emp.getFirstName());

        emp.setFirstName("Jai Ganesh");
        session.getTransaction().begin();
        session.saveOrUpdate(emp);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }

    static public void simpleCriteria() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(employeeRoot);

        EntityManager entityManager = Persistence.createEntityManagerFactory("com.manjesh").createEntityManager();
        List<Employee> resultList = entityManager.createQuery(query).getResultList(); */

        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.gt("salary", 1000));
        criteria.addOrder(Order.desc("id"));

        List<Employee> employees = criteria.list();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        session.close();
        HibernateUtil.shutdown();
    }

    static public void simpleProjection() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.createAlias("department", "_department");
        criteria.add(Restrictions.gt("salary", 1000));
        criteria.addOrder(Order.desc("id"));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.alias(Projections.property("id"), "empId"));
        projectionList.add(Projections.alias(Projections.property("firstName"), "empFirstName"));
        projectionList.add(Projections.alias(Projections.property("salary"), "empSalary"));
        projectionList.add(Projections.alias(Projections.property("_department.deptName"), "empDeptName"));
        criteria.setProjection(projectionList);

        criteria.setResultTransformer(Transformers.aliasToBean(EmployeeDetail.class));

        List<EmployeeDetail> employees = criteria.list();

        for (EmployeeDetail employee : employees) {
            System.out.println(employee.getEmpFirstName() + " ==>" + employee.getEmpDeptName());
        }

        session.close();
        HibernateUtil.shutdown();
    }

    static public void simpleProjectionWithSelectedFields() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.createAlias("department", "_department");
        criteria.add(Restrictions.gt("salary", new BigDecimal(1000)));
        criteria.addOrder(Order.desc("id"));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.alias(Projections.property("id"), "empId"));
        projectionList.add(Projections.alias(Projections.property("firstName"), "empFirstName"));
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List employees = criteria.list();

        System.out.println(employees.toString());

        session.close();
        HibernateUtil.shutdown();
    }

    static public void addJointAccount() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();
        JointAccount j1 = new JointAccount();
        j1.setAccountNumber(123);
        j1.setHusbandName("123");
        j1.setWifeName("123");

        JointAccount j2 = new JointAccount();
        j2.setAccountNumber(123);
        j2.setHusbandName("123");
        j2.setWifeName("123");

        session.saveOrUpdate(j1);
        session.saveOrUpdate(j2);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
