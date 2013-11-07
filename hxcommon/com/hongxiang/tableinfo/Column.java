package com.hongxiang.tableinfo;

public class Column {
    private String name;
    private String dataType;
    private String length;
    private String defaultValue;
    private Boolean primaryIdentifier;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public Boolean getPrimaryIdentifier() {
        return primaryIdentifier;
    }
    public void setPrimaryIdentifier(Boolean primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }
} 