package com.fzk.ioc.beans;

import com.fzk.ioc.beans.def.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象bean工厂
 * map结构存放所有bean实例及相关定义信息
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:39
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    /**
     * map结构作为IoC容器，key为bean名称，value为bean定义（包括bean实例及bean相关信息）
     */
    private Map<String, BeanDefinition> beans = new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * 从IoC容器中获取bean实例
     * 懒加载，若容器中没有，则需要立即创建
     *
     * @param name bean名称
     * @return bean实例
     */
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beans.get(name);
        if (beanDefinition == null)
            throw new Exception("找不到此bean定义:" + name);
        Object bean = beanDefinition.getBean();
        if (bean != null)
            return bean;
        return doCreateBean(beanDefinition);
    }

    /**
     * 根据bean名称，创建bean
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition.getBean() != null)
            return beanDefinition.getBean();
        return beanDefinition.getClassObject().getConstructor().newInstance();
    }

    /**
     * 注册bean定义
     *
     * @param name bean名称
     * @param beanDefinition bean定义信息
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beans.put(name, beanDefinition);
    }
}
