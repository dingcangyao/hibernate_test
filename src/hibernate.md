<h1>Hibernate</h1>  
主流ORM 框架 Object Relation Mapping 对象映射关系, 将面向对象映射成面向关系。  
<h3>如何使用</h3>

1. 导入依赖
2. 创建 Hibernate 配置文件
3. 创建实体类
4. 创建实体类-关系映射类文件
5. 调用 Hibernate API完成操作  

<h3>注意事项</h3>  
- 创建 Maven 工厂
- 实体关系映射文件需要注册到 hibernate 配置文件之中  
- 使用Hibernate API 完成操作
- 如果你要读取 src 中的  xml文件，就需要在pom文件中配置 resources   

<font color="gree">## Hibernatenate级联操作</font>
1. 一对多关系  
    客户和订单：每个客户可以购买多个产品，生成多个订单，但是每个订单只能属于一个客户  
    数据库中 通过主键外键体现。 java 中就是通过集合关联来实现
2. 多对多关系  
    学生选课：一门课程可以被多个学生选择，一个学生可以选择多个课程，都是多。  
    数据库中是通过 两个一对一来对应，创建一个中间表，来关联 学生和课程表。java 其实还是通过集合来进行映射。  

Java 和数据库对于这两种关系的体现完全是两种不同的方式，hibernate 就是负责两者之间的转换

customer  

        <!-- 一对多的映射  customer 是1 order是多  -->
        <set name="orders" table="orders">
            <key column="cid"></key>
            <one-to-many class="com.yaoge.entity.Orders"></one-to-many>
        </set>  
- set 标签来配置实体类中的集合属性 orders
- name 一的一方的实体类属性名
- table 多的一方的表 名
- key 外键
- one-to-many 与集合泛型的实体类对应    
     
   orders

        <!-- 多对一的映射  customer 是1 order是多  -->
        <many-to-one name="customer" class="com.yaoge.entity.Customer" column="cid"></many-to-one>  
- many-to-one 配置实体类对应的对象属性
- name 属性名
- class 属性对应的类
- column 对应一的外键   






