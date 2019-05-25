package com.kyle.config;

import com.kyle.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 指明当前类是一个配置类，就是来代替之前的Spring配置文件
 * 在配置文件中用<bean></bean>标签添加组件
 */
@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中, 容器中组件默认的ID是方法名
     */
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
