package com.fzk.ioc.beans.def;

/**
 * bean定义信息
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:42
 */
public class BeanDefinition {
    // bean实例 TODO 可改为泛型
    private Object bean;
    // bean名称
	private String beanName;
    // bean类型名称
    private String className;
    // bean所属类对象
    private Class<?> classObject;
    // 是否单例
	private boolean singleton = true;
    // bean属性列表
    // 未直接使用List<PropertyValue>，是为了便于封装一些操作
    private PropertyValues propertyValues = new PropertyValues();

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getClassName() {
        return className;
    }

    /**
     * 同时设置class对象和className
     */
    public void setClassName(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        this.className = className;
        this.classObject = clazz;
    }

    public Class<?> getClassObject() {
        return classObject;
    }

    /**
     * 同时设置class对象和className
     */
    public void setClassObject(Class<?> classObject) {
        this.classObject = classObject;
        this.className = classObject.getName();
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}
