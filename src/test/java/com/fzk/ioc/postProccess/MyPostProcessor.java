package com.fzk.ioc.postProccess;

import com.fzk.core.Ordered;
import com.fzk.ioc.beans.factory.PostProcessor;

/**
 * 测试后置处理器
 *
 * author:fanzhoukai
 * 2019/4/27 22:33
 */
public class MyPostProcessor implements PostProcessor, Ordered {
	/**
	 * 初始化前执行
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		System.out.println("拦截到bean:" + beanName + "  初始化前...");
		return bean;
	}

	/**
	 * 初始化后执行
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		System.out.println("拦截到bean:" + beanName + "  初始化后...");
		return bean;
	}

	public int getOrder() {
		return 1;
	}
}
