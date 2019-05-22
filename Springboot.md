# Springboot 入门

## 1. 最简单的Hello World程序



## 2. 主程序，主入口

springboot 运行这个类的main方法来启动Springboot应用。

```java
@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
```

### 2.1 @SpringBootApplication

看一下SpringbootApplication的源码，如下：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
      @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
      @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication{
    ...
}
```

#### 2.1.1 @SpringBootConfiguration

这个注解表示这个类是Springboot的配置类（配置文件）

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
}
```

##### 2.1.1.1 @Configuration

@ Configuration 是Spring定义的注解，@SpringBootConfiguration是springboot定义的注解。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // Configuration也是容器中一个组件
public @interface Configuration {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

#### 2.1.2 @EnableAutoConfiguration

开启自动配置注解，

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
   String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
   Class<?>[] exclude() default {};
   String[] excludeName() default {};
}
```

##### 2.1.2.1 @AutoConfigurationPackage

 自动配置包,将主配置类标注的类所在包及其下所有组件扫描到Spring容器中

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
}
```

​	**@Import** Spring Boot底层注解，给容器中导入一个组件，导入的组件由**AutoConfigurationPackages.Registrar.class**指定。

```java
static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
   @Override
   public void registerBeanDefinitions(AnnotationMetadata metadata,
         BeanDefinitionRegistry registry) {
      register(registry, new PackageImport(metadata).getPackageName());
   }
   @Override
   public Set<Object> determineImports(AnnotationMetadata metadata) {
      return Collections.singleton(new PackageImport(metadata));
   }
}
```

##### 2.1.2.2 @Import(AutoConfigurationImportSelector.class)

给容器中导入组件，AutoConfigurationImportSelector 决定导入哪些组件选择器。

将所有需要导入的组件以全类名的方式返回，这些组件被添加到容器中。

导入自动配置类 xxxAutoConfiguration.

```java
List<String> configurations = SpringFactoriesLoader.loadFactoryNames(
				EnableAutoConfiguration.class, getBeanClassLoader());
```

```
public static List<String> loadFactoryNames(Class<?> factoryClass, @Nullable ClassLoader classLoader) {
   String factoryClassName = factoryClass.getName();
   // 从 META-INF/spring.factories 中获取EnableAutoConfiguration 指定的值，
   return loadSpringFactories(classLoader).getOrDefault(factoryClassName, Collections.emptyList());
}
```

Spring Boot在启动的时候从类路径下的 META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中。spring-boot-autoconfigure-2.1.3.jar;

### 3. SpringBoot 配置文件

Springboot 全局配置文件

- application.properties
- application.yml (YAML: YAML Ain't Markup Language),以数据为中心，比json、xml更适合做配置文件。

### 3.1 YAML 基本语法

- 使用缩进表示层级关系
- 缩进不允许使用Tab键，只运行使用空格
- 缩进的空格数目不重要，只要相同层级的元素左对齐即可
- 大小写敏感

key:(空格)value 表示一对键值对

### 3.2 YAML 支持的三种数据结构

导入配置文件处理器，编写的时候IDE会有提示，不导入也可以

```xml
<!--配置文件处理器，导入就会有提示-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

Bean:

```
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
    // getter ande setter
}

class Dog {
    private String name;
    private Integer age;
    // getter ande setter
}
```

YAML:

```yaml
person:
  lastName: zhangsan
  # last-name: zhangsan 也可以
  age: 18
  boss: false
  maps: {k1: v1, k2: v2}
  lists:
    - list
    - zhaoliu
  dog:
    name: xiaogou
    age: 2
  birth: 2017/12/12
```



#### 3.2.1 对象：键值对的集合(对象，Map)

对象和Map：

```yaml
friends:
  lastName: zhangsan
  age: 20
```

行内写法:

```yaml
friends: {lastName: zhangsan, age: 18}
```



#### 3.2.2 数组：一组按次序排列的值（List、Set）

用 -值表示数组中的一个元素

```yaml
pets:
  - cat
  - dog
  - pig
```

行内写法:

```yaml
pets: [cat,dog,pig]
```



#### 3.2.3 字面量：单个的、不可再分的值(数字，字符串，布尔)

字符串默认不用加上单引号或者双引号

"" : 双引号，不会转义字符串里面的特殊字符串，特殊字符会作为本身想表示的意思

  name: "zhangsan \n lisi" 输出  zhangsan 换行 lisi

'' : 单引号，会转义特殊字符，特殊字符最终只是一个普通的字符串数据

  name: 'zhangsan \n lisi' 输出 zhangsan \n lisi







