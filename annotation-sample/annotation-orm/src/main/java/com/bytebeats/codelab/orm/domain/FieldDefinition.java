package com.bytebeats.codelab.orm.domain;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @date 2017-01-03 18:45
 */
public class FieldDefinition {
    private String name;
    private String alias;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
