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
        People people = new People();//刚new 出来没有设置OID 就是临时状态
//        people.setName("王阳明");
//        people.setMoney(2000.0);
//        session.save(people);//保存之后变成持久态
//        System.out.println(people);
//        session.evict(people);//把指定对象的缓存清除，变成游离态，因为此时的对象有ID，但是建立关系
//        people.setName("王四重");
//        session.update(people);//应该又转换成持久态

        people.setName("haha");
        people.setId(5);
        session.delete(people);
        session.beginTransaction().commit();
        session.close();


    }
}
