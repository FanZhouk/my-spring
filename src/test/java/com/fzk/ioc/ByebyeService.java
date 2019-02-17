package com.fzk.ioc;

import org.junit.Assert;

/**
 * 测试service
 * 用于与HelloService联合，测试循环依赖
 * <p>
 * author:fanzhoukai
 * 2019/2/17 16:22
 */
public class ByebyeService {

    /**
     * 另一个service，用于测试循环依赖
     */
    private HelloService helloService;

    public void sayByebye() {
        Assert.assertNotNull("helloService未注入成功", helloService);
        System.out.println("Byebye");
    }

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
