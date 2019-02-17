package com.fzk.ioc.beans.def;

/**
 * 被依赖的bean对象
 * bean的属性为另一个bean时，使用此类对象代替
 *
 * author:fanzhoukai
 * 2019/2/17 16:47
 */
public class BeanReference {
    /**
     * 被依赖的bean名称
     */
    private String name;
    /**
     * bean实例
     */
    private Object bean;

    public BeanReference() {
    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
