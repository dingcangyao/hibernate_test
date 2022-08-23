package com.yaoge.test;

import com.yaoge.entity.Account;
import com.yaoge.entity.Course;
import com.yaoge.entity.Customer;
import com.yaoge.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

/**
 * create by yaoge
 * 2022/8/22 16:30
 */
public class Test4 {
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
    public void testLazy() {
        /**
         * 你可以看到我在 customer和Order的类中都重写了ToString方法
         * 如果不重写就会报堆栈溢出的错误，因为一直在发送SQL语句
         *
         * 这是因为 你在两个类中都使用了 @Data 注解，会自动帮你重写 toString方法，就会去调用对应的外键
         * 比如  customer 去查询 orders  orders 又回去查询 customer  这样就会一直查询下去，陷入死循环。所以只要不涉及到外键查询就可以了。所以重写toString
         *
         */
        Customer customer = session.get(Customer.class, 1);
        System.out.println(customer);
    }

    /**
     * 测试多的一端懒加载
     */
    @Test
    public void testLazyDuo() {
        Orders orders = session.get(Orders.class, 1);
//        System.out.println(orders.getCustomer().prin());
        orders.getCustomer().prin();
    }


    @After
    public void after() {
        transaction.commit();
        session.close();
    }
}

