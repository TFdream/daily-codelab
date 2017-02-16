package com.bytebeats.code.orm;

import com.bytebeats.code.orm.annotation.Column;
import com.bytebeats.code.orm.annotation.Mapper;
import com.bytebeats.code.orm.domain.FieldDefinition;
import com.bytebeats.code.orm.domain.MapperDefinition;
import com.google.auto.service.AutoService;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 16:39
 */
@AutoService(Processor.class)
public class MapperProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>(2);
        annotataions.add(Mapper.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("MapperProcessor process invoke...");

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Mapper.class);
        if(elements!=null && elements.size()>0){

            List<MapperDefinition> mapperDefinitions = new ArrayList<>();
            for(Element element : elements) {
                if (element.getKind() != ElementKind.CLASS) {
                    error(element, "Only classes can be annotated with @%s", Mapper.class.getSimpleName());
                    return true;
                }

                TypeElement typeElement = (TypeElement) element;
                System.out.println("type:"+typeElement.getQualifiedName());

                MapperDefinition md = new MapperDefinition();
                md.setBeanName(typeElement.getSimpleName().toString());
                md.setBeanQualifiedName(typeElement.getQualifiedName().toString());

                Mapper mapper = typeElement.getAnnotation(Mapper.class);
                md.setPkgName(mapper.pkg());

                List<? extends Element> childEls = typeElement.getEnclosedElements();
                if(childEls!=null && childEls.size()>0){
                    List<FieldDefinition> fieldDefinitions = new ArrayList<>();
                    for(Element child : childEls) {

                        if (child.getKind() != ElementKind.FIELD) {
                            continue;
                        }

                        VariableElement filed = (VariableElement) child;

                        System.out.println("class:"+typeElement.getQualifiedName()+", child:"+filed.getSimpleName());

                        if(filed.getAnnotation(Column.class)!=null){

                            System.out.println("class:"+typeElement.getQualifiedName()+", column:"+filed.getSimpleName()+", type:"+filed.asType().toString());
                            FieldDefinition fd = new FieldDefinition();
                            fd.setName(filed.getSimpleName().toString());
                            fd.setAlias(filed.getAnnotation(Column.class).value());
                            fd.setType(filed.asType().toString());

                            fieldDefinitions.add(fd);
                        }
                    }
                    md.setBeanFields(fieldDefinitions);
                }
                mapperDefinitions.add(md);
            }
            System.out.println("Mapper Definition size:"+mapperDefinitions.size());

            genCode(mapperDefinitions);

            return true;
        }
        return false;
    }

    private static final String SUFFIX = "Mapper";

    private void genCode(List<MapperDefinition> mapperDefinitions) {

        for (MapperDefinition md : mapperDefinitions){

            TypeElement beanClassName = processingEnv.getElementUtils().getTypeElement(md.getBeanQualifiedName());

            System.out.println("beanName:"+md.getBeanName()+", beanClassName:"+beanClassName);

            //包名
            String pkgName = md.getPkgName();
            if(pkgName==null || pkgName.length()<1){
                PackageElement pkg = processingEnv.getElementUtils().getPackageOf(beanClassName);
                if (!pkg.isUnnamed()) {
                    pkgName = pkg.getQualifiedName().toString();
                }
            }

            String mapperClassName = beanClassName.getSimpleName() + SUFFIX;

            System.out.println("mapperClassName:"+mapperClassName+", pkgName:"+pkgName);

            md.setName(mapperClassName);
            md.setQualifiedName(pkgName+mapperClassName);

            /*创建文件，写入代码内容*/
            Writer writer = null;
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(mapperClassName);
                writer = jfo.openWriter();
                writer.write(brewJava(md));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String brewJava(MapperDefinition md) {

        StringBuilder builder = new StringBuilder(1024);
        builder.append("// Generated code from APT. Do not modify!\n");
        if(md.getPkgName()!=null && md.getPkgName().length()>0){
            builder.append("package ").append(md.getPkgName()).append(";\n\n");
        }

        builder.append("import ").append(RowMapper.class.getCanonicalName()).append(";\n");
        builder.append("import java.sql.ResultSet").append(";\n");
        builder.append("import java.sql.SQLException").append(";\n");
        builder.append("import ").append(md.getBeanQualifiedName()).append(";\n\n");

        builder.append("public class ").append(md.getName()).append(" extends RowMapper<").append(md.getBeanName()).append(">").append(" {\n");

        builder.append("\t@Override").append("\n");
        builder.append("\tpublic ").append(md.getBeanName()).append(" toBean(ResultSet rs) throws SQLException {").append("\n");

        builder.append("\t\t").append("return null").append(";\n");;

        builder.append("\t}\n");

        builder.append("}\n");

        System.out.println(builder.toString());

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
