package com.fzk.ioc.context;

import com.fzk.ioc.beans.factory.AbstractBeanFactory;

/**
 * 应用程序上下文抽象类
 * <p>
 * author:fanzhoukai
 * 2019/2/17 17:42
 */
public class AbstractApplicationContext implements ApplicationContext {

    /**
     * bean工厂
     * 将bean工厂对象封装在context中，从而保证IoC容器是单例的
     */
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext() {
    }

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 利用内部bean工厂创建bean
     *
     * @param name bean名称
     * @return bean对象
     */
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    /**
     *
     */
    public void refresh() throws Exception {
    }
}
