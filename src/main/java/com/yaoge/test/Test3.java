package com.yaoge.test;

import com.yaoge.entity.Account;
import com.yaoge.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;

/**
 * create by yaoge
 * 2022/8/22 16:30
 */
public class Test3 {

    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();


        Course course = new Course();
        course.setName("java");

        Account account = new Account();
        account.setName("李四");

        HashSet<Course> courses = new HashSet<>();
        courses.add(course);

        account.setCourses(courses);
        session.save(course);
        session.save(account);
        session.beginTransaction().commit();
        session.close();



    }
}

