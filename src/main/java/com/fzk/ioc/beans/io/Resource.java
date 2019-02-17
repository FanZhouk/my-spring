package com.fzk.ioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 统一资源文件接口
 *
 * author:fanzhoukai
 * 2019/2/17 9:29
 */
public interface Resource {

    /**
     * 获取资源输入流
     */
    InputStream getInputStream() throws IOException;
}
