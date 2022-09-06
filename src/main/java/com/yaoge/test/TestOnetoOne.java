package com.yaoge.test;

import com.yaoge.entity.Customer;
import com.yaoge.entity.Orders;
import com.yaoge.entity.People;
import com.yaoge.one2one.foreign.Department;
import com.yaoge.one2one.foreign.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 16:30
 */
public class TestOnetoOne {
    private Session session;
    private Transaction transaction;


    @Before
    public void begin() {

        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }


    @Test
    public void test(){
        Department department = new Department();
        department.setDeptName("行政");
        Manager manager = new Manager();
        manager.setMgrName("ab");
        department.setMgr(manager);
        manager.setDept(department);
        session.save(manager);
        session.save(department);
    }
    @Test
    public void test2(){

        Department department = session.get(Department.class, 1);
        System.out.println(department.getDeptName()+department.getMgr().getMgrName());
    }

    @After
    public void after() {
        transaction.commit();
        session.close();
    }
}

