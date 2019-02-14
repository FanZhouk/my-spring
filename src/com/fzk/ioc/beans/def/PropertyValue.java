package com.fzk.ioc.beans.def;

/**
 * bean单一属性
 * <p>
 * author:fanzhoukai
 * 2019/2/14 20:58
 */
public class PropertyValue {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段值 TODO 可优化为泛型
     */
    private Object value;

    public PropertyValue() {
    }

    public PropertyValue(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
