package com.fzk.ioc.context;

import com.fzk.ioc.beans.factory.BeanFactory;

/**
 * 应用程序上下文接口
 * <p>
 * 我理解的ApplicationContext的作用是，封装了bean工厂，屏蔽了读取配置文件的实现细节，不需要使用者去考虑注册bean定义等等细节问题。
 * 这使得我们作为调用方，将调用代码最简化，仅提供最少的配置项，达到“一行代码初始化所需环境”的效果，因此该类叫做ApplicationContext（应用程序上下文）。
 * 如下测试代码，仅用第一行初始化了所需的环境，第二行就可以直接获取bean了，是不是很方便：
 * ApplicationContext context = new ClasspathXmlApplicationContext("beans.xml");
 * HelloService helloBean = (HelloService) context.getBean("helloService");
 * <p>
 * author:fanzhoukai
 * 2019/2/17 17:41
 */
public interface ApplicationContext extends BeanFactory {
}
