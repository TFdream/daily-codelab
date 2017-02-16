package com.bytebeats.codelab.orm.domain;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 18:46
 */
public class MapperDefinition {
    private String name;
    private String qualifiedName;
    private String pkgName;

    private String beanName;
    private String beanQualifiedName;

    private List<FieldDefinition> beanFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
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

    public List<FieldDefinition> getBeanFields() {
        return beanFields;
    }

    public void setBeanFields(List<FieldDefinition> beanFields) {
        this.beanFields = beanFields;
    }
}
