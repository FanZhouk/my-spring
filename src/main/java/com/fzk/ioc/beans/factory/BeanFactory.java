package com.fzk.ioc.beans.factory;

/**
 * bean工厂接口
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:33
 */
public interface BeanFactory {
    /**
     * 根据bean名称获取bean实例
     *
     * @param name bean名称
     * @return bean实例
     */
    Object getBean(String name) throws Exception;
}
