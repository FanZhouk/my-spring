package com.fzk.ioc.beans.factory;

/**
 * 后置处理器接口
 *
 * 配置后置处理器方式：
 * 1. 实现此接口，并实现初始化前、初始化后的拦截方法；
 * 2. 将后置处理器声明为bean。
 *
 * author:fanzhoukai
 * 2019/4/27 22:28
 */
public interface PostProcessor {
	/**
	 * 初始化前执行的后置处理器
	 *
	 * @return 返回处理后的bean，禁止为空
	 */
	Object postProcessBeforeInitialization(Object bean, String beanName);

	/**
	 * 初始化后执行的后置处理器
	 *
	 * @return 返回处理后的bean，禁止为空
	 */
	Object postProcessAfterInitialization(Object bean, String beanName);

}
