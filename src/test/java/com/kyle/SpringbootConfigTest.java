package com.kyle;

import com.kyle.bean.Person;
import com.kyle.bean.Person2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring boot 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigTest {

    @Autowired
    Person person;

    @Autowired
    Person2 person2;

    @Value("${name.test1}")
    String name1;

    @Value("${name.test2}")
    String name2;

    @Test
    public void testConfig() {
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(person);
        System.out.println(person2);
    }

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads() {
        System.out.println(ioc.containsBean("helloService"));
    }
}
