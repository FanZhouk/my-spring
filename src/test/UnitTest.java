package test;

import com.fzk.ioc.beans.AutowireCapableBeanFactory;
import com.fzk.ioc.beans.def.BeanDefinition;
import org.junit.Test;

/**
 * 单元测试类
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:21
 */
public class UnitTest {

    private static final String helloServiceBeanName = "helloService";

    @Test
    public void getBean() throws Exception {
        // 创建bean工厂
        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 注册bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassObject(HelloService.class);
        beanFactory.registerBeanDefinition(helloServiceBeanName, beanDefinition);
        // 获取bean
        HelloService bean = (HelloService) beanFactory.getBean(helloServiceBeanName);
        bean.sayHello();
    }
}
