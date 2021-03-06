package com.fzk.ioc.beans.def.reader;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean定义读取工具抽象类
 * <p>
 * author:fanzhoukai
 * 2019/2/17 9:02
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    /**
     * 存储bean定义
     * 此处只存储一个配置文件内的bean定义信息，且不包含bean实例。
     * 此map与AbstractBeanFactory中map的区别是，后者是整个IoC容器，包含全部bean定义信息及bean实例。
     * <p>
     * key:bean名称; value:bean定义
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader() {
    }

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(Map<String, BeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
