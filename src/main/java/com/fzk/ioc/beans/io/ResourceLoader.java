package com.fzk.ioc.beans.io;

import java.net.URL;

/**
 * 统一资源加载工具类
 * <p>
 * author:fanzhoukai
 * 2019/2/17 9:36
 */
public class ResourceLoader {

    /**
     * 根据资源路径获取资源对象
     *
     * @param location 资源路径
     */
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
