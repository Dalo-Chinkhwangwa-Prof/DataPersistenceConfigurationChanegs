package com.bb.datapersistenceconfigchanges.model;

public class Name {

    private String prefix;
    private String actualName;

    public Name(String prefix, String actualName) {
        this.prefix = prefix;
        this.actualName = actualName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }
}
