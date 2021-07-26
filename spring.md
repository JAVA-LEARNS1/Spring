[Core Technologies (spring.io)](https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-setter-injection)

### 1、ioc理论推导

1、UserDao接口

2、UserDaoImpl实现类

3、UserService接口

4、UserServiceImpl实现类

在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改源代码！如果程序代码量十分大，修改一次的成本代价十分昂贵！



我们使用一个set接口实现,已经发生类革命性的变化

```java
    private UserDao userDao;

    //利用set进行动态实现的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
```

- 之前，程序是主动创建对象！控制权在程序猿手上！

- 使用了set注入后，程序不再具有主动性，而是变成类被动的接受对象！

  

这种思想，从本质上解决了问题，我们程序猿不用再去管理对象的创建了。系统的耦合行大大降低，可以更加专注的在业务实现上（横向扩展Mysql，Oracle，Sqlserver），这是IOC原型



**ioc本质**

**控制反转ioc(Inversion of Control),是一种设计思想，DI（依赖注入）是实现Ioc的一种方法，**也有人认为DI只是Ioc的另一种说法。没有Ioc的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓的控制反转就是：获得依赖对象的方式反转类。



采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在spring中实现控制反转的是ioc容器，其实现方法是依赖注入（Dependency Injection,DI）.**



### 2、HelloSpring



**思考问题**

- hello对象是谁创建的？

  hellod对象是由sping创建的

- hellod对象属性是怎么设置的？

  hello对象的属性是由spring容器设置的

这个过程就叫控制反转：

控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用spring后，对象是由spring来创建的

反转：程序本身不创建对象，而变成被动的接收对象，

依赖注入：就是利用set方法进行注入的

IOC是一种编程思想，由主动的编程变成被动的接收

可以通过newClassPathXmlApplicationContext去浏览一下底层源码

**OK，到了现在，我们彻底不用再程序中去改动类，要实现不同的操作，只需要在xml配置文件中进行修改，所谓的ioc，一句话搞定：对象由spring来创建，管理，装配**



### 3、IOC创建对象的方式



​	1、使用无参构造创建对象，默认

​	2、假设我们要使用有参构造创建对象

​		1.下标赋值

```XML
<bean id="user" class="com.ld.pojo.User">
       <constructor-arg index="0" value="LD JAVA"></constructor-arg>
</bean>
```

​		2.通过类型赋值

```xml
<bean id="user" class="com.ld.pojo.User">
    <constructor-arg type="java.lang.String" value="ld java"></constructor-arg>
</bean>
```



​		3、参数名

```xml
<bean id="user" class="com.ld.pojo.User">
    <property name="name" value="LD"></property>
</bean>
```



总结：在配置文件加载的时候，容器中管理的对象就已经初始化了



### 4、Spring 配置

#### 4.1、别名

#### 4.2、Bean的配置

#### 4.3、import

这个import，一般用于团队开发使用，他可以将多个配置文件，导入合并为一个



### 5、依赖注入

#### 5.1、构造器注入

#### 5.2、set方式注入（重点）

- 依赖注入：set注入~
  - 依赖：bean对象的创建依赖于容器
  - 注入：bean对象中的所有属性，由容器来注入

【环境搭建】

1. 复杂类型

   ```JAVA
   public class Address {
       private String address;
   
       public String getAddress() {
           return address;
       }
   
       public void setAddress(String address) {
           this.address = address;
       }
   }
   ```

   

2.  真实的测试对象

   ```java
   public class Student {
       private String name;
       private  Address address;
       private String[] books;
       private List<String> hobbys;
       private Map<String,String> card;
       private Set<String> games;
       private String wife;
       private Properties info;
   }
   ```

3. beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="studen" class="com.ld.pojo.Student">
           <!--第一种 value注入-->
           <property name="name" value="ld"></property>
       </bean>
   </beans>
   ```

4. 测试类

   ```java
   public class MyTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
           Student student = (Student)context.getBean("student");
   
           System.out.println(student.getName());
       }
   }
   ```

   

#### 5.3、拓展方式

我们可以使用p命令空间和c命令空间进行注入

官方解释

![image-20210726202229247](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210726202229247.png)

使用

```XML

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="user" class="com.ld.pojo.User" p:age="2" p:name="ld"></bean>
    <!--C命令使用必须要有有参和无参构成方法-->
    <bean id="user2" class="com.ld.pojo.User" c:age="5" c:name="ld1"></bean>
</beans>
```

测试

```JAVA
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user);
    }
```

注意点：p命名和c命名不能直接使用，需要导入xml约束

```XML
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
```



#### 5.4、bean的作用域

![image-20210726202845843](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210726202845843.png)

​	1、单例模式（spring默认机制）

```XML
<bean id="user2" class="com.ld.pojo.User" c:age="5" c:name="ld1" scope="singleton"></bean>
```

​	2、原型模式

![image-20210726203303826](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210726203303826.png)

```XML
 <bean id="user2" class="com.ld.pojo.User" c:age="5" c:name="ld1" scope="prototype"></bean>
```

​	3、其余模式：request，session，application这些只能在web开发中使用到



### 6、Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给bean装配属性

在Spring中有三种自动装配的方式

​	1、在xml中显示的配置

​	2、在java中显示配置

​	3、隐式的自动装配bean【重要的】



#### 6.1、测试

环境搭建：一个人有两个宠物

#### 6.2、ByName自动装配

```XML
    <!--
    byName会自动在容器上下文中查找 和自己对象set方法后面的值对应的beanid-->
    <bean id="people" class="com.ld.pojo.People" autowire="byName">
        <property name="name" value="ld"></property>
    </bean>
```

#### 6.3、ByType自动装配

```xml
 <bean id="dog2222" class="com.ld.pojo.Dog"></bean>
    <bean id="cat1" class="com.ld.pojo.Cat"></bean>
    <!--
    byName会自动在容器上下文中查找 和自己对象set方法后面的值对应的beanid
    byType会自动在容器上下文中查找 和自己对象属性类型相同的bean
    -->
    <bean id="people" class="com.ld.pojo.People" autowire="byType">
        <property name="name" value="ld"></property>
    </bean>
</beans>
```



小结：

- byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致

- bytype的时候，需要保证所有的bean的class唯一，并且这个bean需要和自动注入的属性的类型一致

  

#### 6.4、使用注解实现自动装配
