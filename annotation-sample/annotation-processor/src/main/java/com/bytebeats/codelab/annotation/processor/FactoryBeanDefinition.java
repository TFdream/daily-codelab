package com.bytebeats.codelab.annotation.processor;

import com.bytebeats.codelab.annotation.Factory;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2016-12-28 17:25
 */
public class FactoryBeanDefinition {
    private String id;
    private String beanName;
    private String beanQualifiedName;
    private String interfaceName;
    private String interfaceQualifiedName;

    public FactoryBeanDefinition(TypeElement classElement) throws IllegalArgumentException {

        Factory annotation = classElement.getAnnotation(Factory.class);
        id = annotation.id();

        if (id==null || id.length()==0) {
            throw new IllegalArgumentException(
                    String.format("id() in @%s for class %s is null or empty! that's not allowed",
                            Factory.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        this.beanName = classElement.getSimpleName().toString();
        this.beanQualifiedName = classElement.getQualifiedName().toString();
        // Get the full QualifiedTypeName
        try {
            Class<?> clazz = annotation.type();
            interfaceQualifiedName = clazz.getCanonicalName();
            interfaceName = clazz.getSimpleName();
        } catch (MirroredTypeException e) {
            DeclaredType classTypeMirror = (DeclaredType) e.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            interfaceQualifiedName = classTypeElement.getQualifiedName().toString();
            interfaceName = classTypeElement.getSimpleName().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanQualifiedName() {
        return beanQualifiedName;
    }

    public void setBeanQualifiedName(String beanQualifiedName) {
        this.beanQualifiedName = beanQualifiedName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceQualifiedName() {
        return interfaceQualifiedName;
    }

    public void setInterfaceQualifiedName(String interfaceQualifiedName) {
        this.interfaceQualifiedName = interfaceQualifiedName;
    }
}
