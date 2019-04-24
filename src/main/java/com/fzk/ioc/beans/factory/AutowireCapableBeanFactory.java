package com.fzk.ioc.beans.factory;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.def.BeanReference;
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
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition.getBean() != null)
            return beanDefinition.getBean();
        // 实例化对象
        Object bean = beanDefinition.getClassObject().getConstructor().newInstance();
        // 放入IoC容器中
	    if (beanDefinition.isSingleton()) {
		    beanDefinition.setBean(bean);
	    }
        // 属性赋值
        applyPropertyValues(beanDefinition, bean);
        return bean;
    }

    // 反射给bean进行属性赋值
    private void applyPropertyValues(BeanDefinition beanDefinition, Object bean) throws Exception {
        for (PropertyValue property : beanDefinition.getPropertyValues().getPropertyValues().values()) {
            Field field = bean.getClass().getDeclaredField(property.getFieldName());
            field.setAccessible(true);

            Object value = property.getValue();
            // 处理bean依赖
            if (value instanceof BeanReference) {
                value = getBean(((BeanReference) value).getName());
            }
            field.set(bean, value);
        }
    }
}
