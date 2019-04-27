package com.fzk.core;

/**
 * 实现此接口的类应该是可被排序的，比如在一个集合中。
 * order可以被理解为优先级，order值越低，优先级越高。
 * <p>
 * author:fanzhoukai
 * 2019/4/27 22:37
 */
public interface Ordered {
	/**
	 * 获取当前对象的order值，值越大排序时越靠后。
	 * 通常从0或1开始，最大为Integer.MAX_VALUE。
	 * <p>
	 * order值相同的对象，优先级随机。
	 * 值越高说明优先级越低，有点类似于servlet启动时的load-on-startup参数。
	 *
	 * @return order值
	 */
	int getOrder();
}
