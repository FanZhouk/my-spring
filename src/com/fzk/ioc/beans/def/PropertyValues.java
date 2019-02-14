package com.fzk.ioc.beans.def;

import java.util.HashMap;

/**
 * bean属性列表
 * <p>
 * author:fanzhoukai
 * 2019/2/14 20:57
 */
public class PropertyValues {
    /**
     * bean属性列表
     */
    private final HashMap<String, PropertyValue> propertyValues = new HashMap<String, PropertyValue>();

    /**
     * 添加属性
     *
     * @param fieldName 属性名
     * @param value     属性值
     */
    public PropertyValue addPropertyValue(String fieldName, Object value) {
        return propertyValues.put(fieldName, new PropertyValue(fieldName, value));
    }

    /**
     * 根据名称删除属性
     *
     * @param fieldName 属性名
     * @return 删除成功返回true，否则返回false
     */
    public boolean deletePropertyValue(String fieldName) {
        return propertyValues.remove(fieldName) != null;
    }

    /**
     * 判断是否已存在属性定义
     *
     * @param fieldName 属性名
     * @return 存在则返回true，否则false
     */
    public boolean contains(String fieldName) {
        return propertyValues.containsKey(fieldName);
    }

    public HashMap<String, PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
