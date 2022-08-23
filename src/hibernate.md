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


<h3>Hibernate延迟加载</h3>   
延迟加载、惰性加载、懒加载    
使用延迟加载可以提高程序的运行效率，Java程序与数据库交互的频次越低，程序运行的效率就越高，所以我们应该尽量减少java程序与数据库的交互次数
Hibernate 延迟加载就很好的做到了这一点。  
客户和订单，当我们查询客户对象的时候，因为有级联设置，所以会将对应的订单信息
一块查出来，这样就需要发送两条SQL语句，分别查询客户信息的订单信息。   
   
延迟加载的思路是：当我们查询客户的时候，如果没有访问订单的数据只发送一条SQL语句查询客户信息。如果
需要访问订单数据，则发送两条SQL。

延迟加载可以看作是一种优化机制，根据具体的需求，自动选择要执行的SQL语句数量。
   
### 例子
一对多   
1. 查询 Customer，对orders进行延迟加载设置，在 customer.hbm.xml中进行设置，延迟加载默认就是开启的。
2. 查询 Customer
  
hibernate配置文件
1. hibernate.cfg.xml(全局配置文件)
2. hbm.xml(关系映射文件)  

全局环境  
1. 数据库的基本信息
2. 设置数据库连接池信息（c3p0）
3. hibernate基本信息
4. 注册实体配置文件   

实体关系映射文件详细信息  
### hibernate-mapping 属性
- package 给class节点实体类统一设置报名，设置了package 虾米那的class name 就可以省略包名
- schema：数据库 schema的名称
- catalog ：数据库 catalog的名称
- default-cascade：默认的级联关系，默认为none
- default-access：Hibernate 用来访问属性的策略
- default-lazy：指明了未明确标注lazy属性的java 属性和集合类，Hibernate会采用什么样的加载风格，默认为true
- auto-import：指定我们是否可以在查询语句中使用非全限定类名，默认为true，如果项目中有两个同名的持久化类，最好在这两个类的对应映射文件中配置为false

### class 属性
- name：实体类名
- table：数据表名
- schema
- catalog：这两个和上面 hibernate-mapping都是一样的，只是优先级更高
- dynamic-update:动态更新
- dynamic-insert：动态添加  所谓的动态就是根据数据的情况去生成SQL语句，而不是固定的生成代码
- where:就是查询时给SQL添加where语句

### id属性
- name ：实体类属性名
- type：实体类属性数据类型   
此处可以设置两种类型的数据：Java数据类型或者Hibernate映射类型  
实体类的属性数据类型必须与数据表对那个的字段数据类型一致：  
int 对应 int，String 对应 varchar  
如何进行映射？ java数据类型映射到Hibernate映射类型，再由hibernate映射类型映射到SQL数据类型  
java----》Hibernate----》SQL  
- column：数据库表的主键字段名
- generator：主键生成策略
1. hilo 算法
2. increment：Hibernate自增
3. identity：数据库自增
4. native：本地策略，根据底层数据库自动选择主键的生成策略
5. uuid.hex算法
6. select 算法

###property属性  
- name:实体类的属性名
- column：数据表字段名
- type:数据类型
- updae：该字段是否可以修改，默认为true
- insert：该字段是否可以添加，默认为true
- lazy：延迟加载策略

###实体关系映射文件属性
1. inverse  
Customer和Orders 是一对多关系，一个Customer对应多个Orders，实体类中用一个set集合来表示对应的Orders。  
Customer.hbm.xml中使用set标签来配置映射关系。两者都在维护一个一对多的关系，有可能会出现重复维护的问题。
如何避免这种情况：1 java代码中放弃维护  2.在配置文件中放弃维护，哪一方放弃维护，就设置inverse=true  
inverse 属性是用来设置是否将维护权利交给对方，默认是false，不交出维护权，双方都在维护。设置为true 就放弃维护
2. cascade ：用来设置级联操作，就是当有外键的情况，就可以级联删除对应的记录。





