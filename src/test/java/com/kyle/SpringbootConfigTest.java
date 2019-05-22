package com.kyle;

import com.kyle.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring boot 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigTest {

    @Autowired
    Person person;

    @Test
    public void testConfig() {
        System.out.println(person);
    }
}
