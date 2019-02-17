package com.fzk.ioc;

import org.junit.Assert;

/**
 * 测试service
 * IoC容器会自动创建该类的对象
 * <p>
 * author:fanzhoukai
 * 2019/2/13 21:34
 */
public class HelloService {
    /**
     * 普通bean属性，用于测试属性赋值
     */
    private String name;

    /**
     * 另一个service，用于测试循环依赖
     */
    private ByebyeService byebyeService;

    public void sayHello() {
        Assert.assertNotNull("byebyeService未注入成功", byebyeService);
        System.out.println("Hello, my name is " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ByebyeService getByebyeService() {
        return byebyeService;
    }

    public void setByebyeService(ByebyeService byebyeService) {
        this.byebyeService = byebyeService;
    }
}
