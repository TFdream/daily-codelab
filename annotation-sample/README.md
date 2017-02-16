## Java Annotation
### 基本概念

Java 注解（Annotation）分为两类：编译时（Compile time）处理的注解和在运行时（Runtime）通过反射机制运行处理的注解。本文将重点介绍在编译时（Compile time）处理的注解，关于在运行时（Runtime）通过反射机制运行处理的注解，比较简单这里不做介绍大家可以自行找资料学习。

注解处理器（Annotation Processor）是javac的一个工具，它用来在编译时扫描和处理注解(Annotation). 你可以自定义注解, 在编译期生成新的Java文件。

在Java 5首次引入注解的时候, 注解处理器的API还没有成熟, 也没有标准化. 处理注解需要一个名为apt（也就是Annotation Processor Tool, 注解处理器工具）的独立的工具, 以及包含在com.sum.mirror包中的Mirror API. apt需要使用Mirror API来自定义处理器.

从Java 6开始，注解处理器通过 [JSR 269] (https://www.jcp.org/en/jsr/detail?id=269) 已经标准化并被纳入到标准库中，APT工具也被无缝集成到Java编译工具javac里面。Java 6提供了一个已经实现通用功能的抽象类javax.annotation.processing.AbstractProcessor，同时还提供了javax.lang.model包。

### 实现一个注解处理器
#### 1. 自定义注解
```
package com.bytebeats.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ricky Fung
 * @date 2016-12-28 11:26
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
public @interface Factory {
    String id() default "";
    Class<?> type();
}
```

Retention注解有一个属性value, 接受RetentionPolicy类型的值, RetentionPolicy是一个枚举类型, 有3个值: SOURCE, CLASS, RUNTIME
* RetentionPolicy.SOURCE: 表示编译时注解的信息会被编译器抛弃, 不会留在class文件中, 注解的信息只会留在源文件中;
* RetentionPolicy.CLASS: 表示编译时注解的信息被保留在class文件(字节码文件)中, 但不会被虚拟机读取在运行的时候;
* RetentionPolicy.RUNTIME: 表示编译时注解的信息被保留在class文件(字节码文件)中, 并且会被虚拟机保留在运行时, 可以用反射的方式读取;

#### 2. 编写注解处理器
每一个处理器都是继承于AbstractProcessor，如下所示：
```
package com.bytebeats.apt;

@SupportedAnnotationTypes({ "com.bytebeats.apt.Factory" })
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FactoryProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment env){ }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) { }

}
```
@SupportedAnnotationTypes定义要支持注解的完整路径, 也可以通过getSupportedAnnotationTypes()方法来定义;
@SupportedSourceVersion(SourceVersion.RELEASE_7)表示支持的jdk的版本, 也可以通过getSupportedSourceVersion()方法来定义;

<br>
因为兼容的原因, 特别是针对Android平台, 我建议使用重载getSupportedAnnotationTypes()和getSupportedSourceVersion()方法代替@SupportedAnnotationTypes和@SupportedSourceVersion. 如下:
```
package com.bytebeats.apt;

public class FactoryProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment env){ }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) { }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>(2);
        annotataions.add(Factory.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
```

#### 3. 注册注解处理器
如何注册你的处理器到javac中?
compiler生成的.jar中, 在META-INF/services目录需要新建一个特殊的文件javax.annotation.processing.Processor，文件里的内容就是声明你的处理器.
<br>
.jar文件目录结构如下:
<br>
\- com<br>
\- \- bytebeats<br>
\- \- \- apt<br>
\- \- \- \- FactoryProcessor<br>
\- META-INF<br>
\- \- services<br>
\- \- \- javax.annotation.processing.Processor

<br>
javax.annotation.processing.Processor文件的内容是注解处理器的合法的全名列表，每一个元素换行分割：
```
com.bytebeats.apt.FactoryProcessor
```

使用google的auto-service可以自动生成META-INF/services/javax.annotation.processing.Processor文件, 如下:

```
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }
    
    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
    
    }
}
```
需要增加Maven依赖:
```
<dependency>
    <groupId>com.google.auto.service</groupId>
    <artifactId>auto-service</artifactId>
    <version>1.0-rc2</version>
</dependency>
```
