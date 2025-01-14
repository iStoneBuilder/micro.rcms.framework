package com.stone.it.rcms.core.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作权限注解
 *
 * @author cj.stone
 * @Desc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RcmsMethod {

    /**
     * 方法名，不设置时使用接口方法名
     */
    String[] name();

    /**
     * 是否为openApi N（默认）：不为openApi Y：为openApi
     */
    String[] type() default "N";

}

/*
    什么是注解？
      对于很多初次接触的开发者来说应该都有这个疑问？Annontation是Java5开始引入的新特征，中文名称叫注解。它提供了一种安全的类似注释的机制，
      用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量等）进行关联。为程序的元素（类、方法、成员变量）加上更直观更明了的
      说明，这些说明信息是与程序的业务逻辑无关，并且供指定的工具或框架使用。

      Annontation 像一种修饰符一样，应用于包、类型、构造方法、方法、成员变量、参数及本地变量的声明语句中。 Java注解是附加在代码中的一些元信
      息，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。注解不会也不能影响代码的实际逻辑，仅仅起到辅助性的作用。包含在
      java.lang.annotation包中。

    注解的用处：
      1、生成文档。这是最常见的，也是java 最早提供的注解。常用的有@param @return 等 2、跟踪代码依赖性，实现替代配置文件功能。比如Dagger 2
      依赖注入，未来java 开发，将大量注解配置，具有很大用处; 3、在编译时进行格式检查。如@override 放在方法前，如果你这个方法并不是覆盖了超类方
      法，则编译时就能检查出。

    注解的原理：
      注解本质是一个继承了Annotation 的特殊接口，其具体实现类是Java 运行时生成的动态代理类。而我们通过反射获取注解时，返回的是Java 运行时生成
      的动态代理对象$Proxy1。通过代理对象调用自定义注解（接口）的方法，会最终调用AnnotationInvocationHandler 的invoke方法。该方法会从
      memberValues 这个Map 中索引出对应的值。而memberValues 的来源是Java 常量池。

    元注解：
      java.lang.annotation 提供了四种元注解，专门注解其他的注解（在自定义注解的时候，需要使用到元注解）：

      @Documented – 注解是否将包含在JavaDoc中
      @Retention – 什么时候使用该注解
      @Target – 注解用于什么地方
      @Inherited – 是否允许子类继承该注解
    1.@Retention – 定义该注解的生命周期

      RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
      RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
      RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。

    2.@Target – 表示该注解用于什么地方。默认值为任何元素，表示该注解用于什么地方。可用的ElementType 参数包括
      ElementType.CONSTRUCTOR: 用于描述构造器
      ElementType.FIELD: 成员变量、对象、属性（包括enum实例）
      ElementType.LOCAL_VARIABLE: 用于描述局部变量
      ElementType.METHOD: 用于描述方法
      ElementType.PACKAGE: 用于描述包
      ElementType.PARAMETER: 用于描述参数
      ElementType.TYPE: 用于描述类、接口(包括注解类型) 或enum声明
    3.@Documented – 一个简单的Annotations 标记注解，表示是否将注解信息添加在java 文档中。

    4.@Inherited – 定义该注释和子类的关系
      @Inherited 元注解是一个标记注解，@Inherited 阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited 修饰的annotation 类型被用于一个class，
      则这个annotation 将被用于该class 的子类。

    常见标准的Annotation：
      1.Override

      java.lang.Override是一个标记类型注解，它被用作标注方法。它说明了被标注的方法重载了父类的方法，起到了断言的作用。如果我们使用了这种注解在一个
      没有覆盖父类方法的方法时，java 编译器将以一个编译错误来警示。

      2.Deprecated

      Deprecated 也是一种标记类型注解。当一个类型或者类型成员使用@Deprecated 修饰的话，编译器将不鼓励使用这个被标注的程序元素。所以使用这种修饰
      具有一定的“延续性”：如果我们在代码中通过继承或者覆盖的方式使用了这个过时的类型或者成员，虽然继承或者覆盖后的类型或者成员并不是被声明为
      @Deprecated，但编译器仍然要报警。

      3.SuppressWarnings

      SuppressWarning 不是一个标记类型注解。它有一个类型为String[] 的成员，这个成员的值为被禁止的警告名。对于javac 编译器来讲，被-Xlint 选项有效的
      警告名也同样对@SuppressWarings 有效，同时编译器忽略掉无法识别的警告名。 `@SuppressWarnings("unchecked")
*/
