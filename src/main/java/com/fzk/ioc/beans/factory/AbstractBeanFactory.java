package com.fzk.ioc.beans.factory;

import com.fzk.ioc.beans.def.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
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
    protected Map<String, BeanDefinition> beans = new ConcurrentHashMap<String, BeanDefinition>();

	/**
	 * 后置处理器list
	 */
	protected List<PostProcessor> postProcessors = new ArrayList<PostProcessor>();

	/**
	 * 从IoC容器中获取bean实例
     * 懒加载，若容器中没有，则需要立即创建
     *
     * @param name bean名称
     * @return bean实例
     */
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beans.get(name);
        // TODO 引入自己的异常机制
        if (beanDefinition == null)
            throw new Exception("找不到此bean定义:" + name);
        Object bean = beanDefinition.getBean();
        if (bean != null)
            return bean;
        return doCreateBean(beanDefinition);
    }

    /**
     * 根据bean定义信息创建bean
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

	/**
	 * 找到符合指定类型的所有bean名称
	 * 符合类型是指，属于指定类及其子类，或是指定接口的实现类
	 */
	public abstract String[] getBeanNames(Class<?> clazz);

    /**
     * 注册bean定义
     *
     * @param name           bean名称
     * @param beanDefinition bean定义信息
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beans.put(name, beanDefinition);
    }

    /**
     * 预先初始化全部bean
     */
    public void preInstantiateSingletons() throws Exception {
	    for (Map.Entry<String, BeanDefinition> entry : beans.entrySet()) {
		    if (entry.getValue().isSingleton()) {
		    	getBean(entry.getKey());
		    }
	    }
    }

	/**
	 * 添加后置处理器
	 */
	public void addPostProcessors(List<PostProcessor> list) {
		this.postProcessors.addAll(list);
	}
}
