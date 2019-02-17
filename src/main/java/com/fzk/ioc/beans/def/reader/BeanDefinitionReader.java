package com.fzk.ioc.beans.def.reader;

import javax.xml.parsers.ParserConfigurationException;

/**
 * bean定义读取工具接口
 * 子类会实现从不同位置读取配置，组装为bean定义的方法
 * <p>
 * author:fanzhoukai
 * 2019/2/17 8:50
 */
public interface BeanDefinitionReader {
    public void loadBeanDefinitions(String location) throws Exception;
}
