package com.fzk.ioc.beans;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.def.PropertyValue;

import java.lang.reflect.Field;

/**
 * 有自动注入功能的bean工厂
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:56
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 根据bean定义信息创建bean
     * 执行过程是：实例化->放入IoC容器->属性赋值
     * 属性赋值之前就放入了IoC容器，这样就保证了不会出现循环依赖问题！
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition.getBean() != null)
            return beanDefinition.getBean();
        // 实例化对象
        Object bean = beanDefinition.getClassObject().getConstructor().newInstance();
        // 放入IoC容器中
        beanDefinition.setBean(bean);
        // 属性赋值
        setPropertyValues(beanDefinition, bean);
        return bean;
    }

    // 反射给bean进行属性赋值
    private void setPropertyValues(BeanDefinition beanDefinition, Object bean) throws NoSuchFieldException, IllegalAccessException {
        for (PropertyValue property : beanDefinition.getPropertyValues().getPropertyValues().values()) {
            Field field = bean.getClass().getDeclaredField(property.getFieldName());
            field.setAccessible(true);
            field.set(bean, property.getValue());
        }
    }
}
