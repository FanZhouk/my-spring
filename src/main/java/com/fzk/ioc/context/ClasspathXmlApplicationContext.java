package com.fzk.ioc.context;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.def.reader.XmlBeanDefinitionReader;
import com.fzk.ioc.beans.factory.AbstractBeanFactory;
import com.fzk.ioc.beans.factory.AutowireCapableBeanFactory;
import com.fzk.ioc.beans.io.ResourceLoader;

import java.util.Map;

/**
 * 从xml配置文件中读取bean信息的上下文对象
 * <p>
 * author:fanzhoukai
 * 2019/2/17 17:46
 */
public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

    /**
     * xml配置文件路径
     */
    private String configLocation;

    /**
     * 根据配置文件路径，构造上下文对象
     *
     * @param configLocation 配置文件路径
     */
    public ClasspathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    /**
     * 根据配置文件路径和bean工厂，构造上下文对象
     *
     * @param configLocation 配置文件路径
     * @param beanFactory    bean工厂对象
     */
    public ClasspathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    /**
     * 将xml中读取到的bean定义，全部注册（即转移）到bean工厂中的map中
     * <p>
     * 这是因为，xml中读取到的bean定义只是bean定义，不会包括bean实例，
     * 调用getBean方法时，只会从bean工厂中的map里获取bean定义并初始化。因此需要一次转移操作。
     * <p>
     * bean定义的流转过程如下：
     * xml配置文件 --> BeanDefinition对象 --> 注册进bean工厂 --> getBean()实例化、初始化 --> 使用 --> 销毁
     * 此方法就是从第二步到第三步的过程。
     */
    @Override
    public void refresh() throws Exception {
    	// 读取xml中的bean定义至reader中
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions(configLocation);

        // 注册bean定义至IoC容器缓存中
        for (Map.Entry<String, BeanDefinition> entry : reader.getBeanDefinitionMap().entrySet()) {
            super.beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        // 预先实例化所有单例bean
	    beanFactory.preInstantiateSingletons();
    }
}
