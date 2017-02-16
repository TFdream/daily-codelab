package com.bytebeats.codelab.annotation.processor;

import com.bytebeats.codelab.annotation.Factory;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 注解处理器
 *
 * @author Ricky Fung
 * @date 2016-12-28 11:19
 */
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor {

    public static final String SUFFIX = "Factory";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

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

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("FactoryProcessor process invoke...");

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Factory.class);
        if(elements!=null && elements.size()>0){

            Map<String, FactoryBeanDefinition> factoryBeanDefinitionMap = new LinkedHashMap<>();

            for (Element element : elements) {

                if (element.getKind() != ElementKind.CLASS) {
                    error(element, "Only classes can be annotated with @%s", Factory.class.getSimpleName());
                    return true;
                }

                TypeElement typeElement = (TypeElement) element;
                System.out.println("process:"+typeElement.getSimpleName()+","+typeElement.getQualifiedName());

                if (!element.getModifiers().contains(Modifier.PUBLIC)) {
                    throw new IllegalArgumentException(String.format("The class %s is not public.",
                            typeElement.getQualifiedName().toString()));
                }
                if (typeElement.getModifiers().contains(Modifier.ABSTRACT)) {   // 检查是否是一个抽象类
                    throw new IllegalArgumentException(String.format("The class %s is abstract. You can't annotate abstract classes with @%",
                            typeElement.getQualifiedName().toString(), Factory.class.getSimpleName()));
                }

                FactoryBeanDefinition beanDefinition = new FactoryBeanDefinition(typeElement);

                System.out.println("id:"+beanDefinition.getId()+", class:"+beanDefinition.getBeanQualifiedName()+
                        ", interface class:"+beanDefinition.getInterfaceQualifiedName());

                if(factoryBeanDefinitionMap.containsKey(beanDefinition.getId())){
                    throw new IllegalArgumentException(String.format("id: %s already exists", beanDefinition.getId()));
                }

                factoryBeanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
            }

            System.out.println("factoryBeanDefinitionMap size"+factoryBeanDefinitionMap.size());
            genCode(factoryBeanDefinitionMap);
            return true;
        }

        return false;
    }

    private void genCode(Map<String, FactoryBeanDefinition> factoryBeanDefinitionMap) {

        String interfaceName = null, pkgName = null;
        for (String id : factoryBeanDefinitionMap.keySet()) {

            FactoryBeanDefinition fbd = factoryBeanDefinitionMap.get(id);

            TypeElement beanClassElement = processingEnv.getElementUtils().getTypeElement(fbd.getBeanQualifiedName());
            interfaceName = fbd.getInterfaceName();

            // 包名
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(beanClassElement);
            if (!pkg.isUnnamed()) {
                pkgName = pkg.getQualifiedName().toString();
            }
            System.out.println("pkgName:"+pkgName + ", interfaceName:" + interfaceName);
            break;
        }

        /*创建文件，写入代码内容*/
        Writer writer = null;
        try {
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(interfaceName + SUFFIX);
            writer = jfo.openWriter();
            writer.write(brewJava(pkgName, interfaceName, factoryBeanDefinitionMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String brewJava(String pkgName, String interfaceName, Map<String, FactoryBeanDefinition> factoryBeanDefinitionMap) {

        StringBuilder builder = new StringBuilder(1024);
        builder.append("// Generated code from APT. Do not modify!\n");
        if(pkgName!=null && pkgName.length()>0){
            builder.append("package ").append(pkgName).append(";\n\n");
        }
        String className = interfaceName + SUFFIX;
        builder.append("public class ").append(className).append(" {\n");
        builder.append("\t public ").append(interfaceName).append(" create(").append("String id").append(") { \n");

        builder.append("\t \t if (id == null)\n");
        builder.append("\t \t throw new IllegalArgumentException(\"id is null!\");\n");

        for(Map.Entry<String, FactoryBeanDefinition> me : factoryBeanDefinitionMap.entrySet()){
            builder.append(String.format("\t \t if (\"%s\".equals(id))\n", me.getKey()));
            builder.append(String.format("\t \t return new %s();\n", me.getValue().getBeanQualifiedName()));
        }

        builder.append("\t \t throw new IllegalArgumentException(\"Unknown id = \" + id);\n");

        builder.append("\t }\n");

        builder.append("}\n");

        return builder.toString();
    }

    /**log**/
    private void error(Element e, String msg, Object... args) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
