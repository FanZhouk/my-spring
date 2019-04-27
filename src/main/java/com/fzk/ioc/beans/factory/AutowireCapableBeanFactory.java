package com.fzk.ioc.beans.factory;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.def.BeanReference;
import com.fzk.ioc.beans.def.PropertyValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 有自动注入功能的bean工厂
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:56
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 根据bean定义信息创建bean（保证为新创建，而不是缓存中已有的）
     * 执行过程是：实例化->放入IoC容器->属性赋值
     * 属性赋值之前就放入了IoC容器，这样就保证了不会出现循环依赖问题！
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        // 实例化对象
        Object bean = beanDefinition.getClassObject().getConstructor().newInstance();
        // 放入IoC容器中
	    if (beanDefinition.isSingleton()) {
		    beanDefinition.setBean(bean);
	    }
        // 属性赋值
        applyPropertyValues(beanDefinition, bean);

	    // 后置处理器
	    bean = applyBeanPostProcessorsBeforeInitialization(bean, beanDefinition.getBeanName());
	    bean = applyBeanPostProcessorsAfterInitialization(bean, beanDefinition.getBeanName());
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

    // 调用初始化前的后置处理器
	private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws Exception {
		for (PostProcessor pp : this.postProcessors) {
			bean = pp.postProcessBeforeInitialization(bean, beanName);
			// TODO 替换为自己的异常体系
			if (bean == null) {
				throw new Exception("后置处理器返回结果不能为空");
			}
		}
		return bean;
	}

	// 调用初始化后的后置处理器
	private Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) throws Exception {
		for (PostProcessor pp : this.postProcessors) {
			bean = pp.postProcessAfterInitialization(bean, beanName);
			// TODO 替换为自己的异常体系
			if (bean == null) {
				throw new Exception("后置处理器返回结果不能为空");
			}
		}
		return bean;
	}

	/**
	 * 找到符合指定类型的所有bean名称
	 * 符合类型是指，属于指定类及其子类，或是指定接口的实现类
	 */
	public String[] getBeanNames(Class<?> clazz) {
		List<String> result = new ArrayList<String>();
		for (BeanDefinition bd : super.beans.values()) {
			if (clazz.isAssignableFrom(bd.getClassObject())) {
				result.add(bd.getBeanName());
			}
		}
		return result.toArray(new String[result.size()]);
	}
}
