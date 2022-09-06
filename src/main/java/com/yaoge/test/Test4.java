package com.yaoge.test;

import com.yaoge.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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

    /**
     * 插入数据
     */
    @Test
    public void testI() {

        People people = new People();
        people.setName("张双江");
        people.setMoney(4563.0);
        session.save(people);
    }


    /**
     * 测试HQL   查询 People 表中全部数据
     */
    @Test
    public void testHQL1() {

        String hql = "from People";//这里一定要写类名，可以不写全类名
        Query query = session.createQuery(hql);
        List list = query.list();
        list.stream().forEach((item) -> System.out.println(item));


    }

    /**
     * 测试HQL   分页查询
     */
    @Test
    public void testHQLPage() {

        String hql = "from People";//这里一定要写类名，可以不写全类名
        Query query = session.createQuery(hql);
        query.setFirstResult(3);//设置起始查询记录，就是从第几个查询
        query.setMaxResults(3);//每次查询几个

        List list = query.list();
        list.stream().forEach((item) -> System.out.println(item));


    }

    /**
     * 测试HQL   where 查询
     * 使用方式和 SQL 没有区别
     */
    @Test
    public void testHQLWhere() {

        String hql = "from People where id=3";//这里一定要写类名，可以不写全类名
        Query query = session.createQuery(hql);
        List list = query.list();//返回一个集合
        //        query.uniqueResultOptional()
        //        query.uniqueResult()
        //如果只是查询单条记录，可以使用上面两个方法，返回为空

        list.stream().forEach((item) -> System.out.println(item));


    }
    /**
     * 测试HQL  模糊查询
     */
    @Test
    public void testHQLLike() {

        String hql = "from People where name like '%双%'";//这里一定要写类名，可以不写全类名
        Query query = session.createQuery(hql);
        List list = query.list();//返回一个集合
        //        query.uniqueResultOptional()
        //        query.uniqueResult()
        //如果只是查询单条记录，可以使用上面两个方法，返回为空

        list.stream().forEach((item) -> System.out.println(item));


    }
    /**
     * 测试HQL  Order By 排序
     * 也是跟SQL是一样的使用
     */
    @Test
    public void testHQLOrder() {

        String hql = "from People order by money desc ";
        //desc 是倒序， asc（默认） 是正序
        Query query = session.createQuery(hql);
        List list = query.list();//返回一个集合

        list.stream().forEach((item) -> System.out.println(item));


    }
   /**
     * 测试HQL
    * 查询单一属性
    * 需要用到
     */
    @Test
    public void testHQLAttribute() {

        String hql = "select name from People where id=4 ";
        Query query = session.createQuery(hql);
//        List<String> list = query.list();  //查询多个记录的单属性，就是直接用list接收就行了
//        System.out.println(list);
        String name = (String) query.uniqueResult();
        // query.uniqueResult 返回值就只能是单个对象，如果结果有多个，就会报错
//        List<People> list = query.list();//其实不指定类型，默认的转成的就是Object数组
        System.out.println(name);
    }
  /**
     * 测试HQL
   * 测试占位符
     */
    @Test
    public void testHQLZhanwei() {

        String hql = " from People where name=:name";
        Query query = session.createQuery(hql);
        query.setString("name","张三");
        List<People> list = query.list();
        System.out.println(list);
    }
  /**
     * 测试HQL
   * 级联查询, 就是参数可以直接设置为实体
     */
    @Test
    public void testHQLCascade() {

        String hql1 = " from Customer where name=:name";
        Query query1 = session.createQuery(hql1);
        query1.setString("name","李四");
        List<Customer> list = query1.list();
        System.out.println("用户："+list);
        list.forEach(element->{
            String hql2 = " from Customer where name=:name";
            Query query2 = session.createQuery(hql2);
            query2.setString("name","李四");
            List<Customer> list2 = query2.list();
            System.out.println("对应的订单");
            System.out.println(list2);
        });
    }
    /**
     * 测试delete的rollback属性
     */
    @Test
    public void testD1() {

        People people = new People();

        people.setId(2);
        System.out.println(people);
        session.delete(people);
        System.out.println(people);
    }
    /**
     * 测试更新单属性
     * 即使时 dynamic update 和insert 也不能根据游离对象的null值
     * 来不设置 对应的属性
     */
    @Test
    public void testU1() {

        People people = new People();

        people.setId(1);

        people.setName("李fd");
//        people.setMoney(null);
        session.update(people);
        System.out.println(people);
    }
    /**
     * 测试懒加载
     */
    @Test
    public void testLazy1() {

        Customer customer1 = session.load(Customer.class, 2);
        System.out.println(customer1.getName());
        Set<Orders> order = customer1.getOrders();
        Orders orders = session.load(Orders.class, 2);

        Customer customer = orders.getCustomer();
        System.out.println(orders);


    }

    @After
    public void after() {
        transaction.commit();
        session.close();
    }
}

