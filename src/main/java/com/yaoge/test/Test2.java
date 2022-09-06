package com.yaoge.test;

import com.yaoge.entity.Customer;
import com.yaoge.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.Order;

/**
 * create by yaoge
 * 2022/8/22 15:57
 */
public class Test2 {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();

//        //创建 Customer
//        Customer customer = new Customer();
//        customer.setName("张三");
//        //创建Orders
//        Orders orders = new Orders();
//        orders.setName("订单1");
//
//        //建立关联关系
//        orders.setCustomer(customer);
//        //保存
//        session.save(customer);
//        session.save(orders);
//        customer.setName("李四");//持久态的对象更改属性会在提交事务之后保存到数据库



        //提交事务
        transaction.commit();
        //关闭session
        session.close();
    }
}
