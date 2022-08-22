package com.yaoge.test;

import com.yaoge.entity.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * create by yaoge
 * 2022/8/22 14:52
 */
public class Test {
    public static void main(String[] args) {

        //创建 Configuration的对象
        Configuration configuration = new Configuration().configure();
        //获取SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取Session
        Session session = sessionFactory.openSession();
        People people = new People();
        people.setName("李四");
        people.setMoney(2000.0);
        session.save(people);
        session.beginTransaction().commit();
        session.close();


    }
}
