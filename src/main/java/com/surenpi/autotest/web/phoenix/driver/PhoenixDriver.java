/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.surenpi.autotest.web.phoenix.driver;

import java.util.List;

import com.surenpi.autotest.webui.ui.Element;

/**
 * webdriver接口.
 * @author suren
 * @since 2017年8月2日 下午12:45:32
 */
public interface PhoenixDriver
{
    /**
     * 新建会话
     * @return 会话
     */
    PhoenixSession newSession();
    
    /**
     * 打开地址.
     * @param url 页面地址
     * @return 驱动接口
     */
    PhoenixDriver open(String url);
    
    /**
     * 刷新页面.
     * @return 驱动接口
     */
    PhoenixDriver refresh();
    
    /**
     * 浏览器向前.
     * @return 驱动接口
     */
    PhoenixDriver forward();
    
    /**
     * 浏览器向后.
     * @return 驱动接口
     */
    PhoenixDriver back();
    
    /**
     * 获取页面标题（title）.
     * @return 页面标题（title）
     */
    String getTitle();
    
    /**
     * 当前url.
     * @return 当前url
     */
    String getUrl();
    
    /**
     * 获取页面源码.
     * @return 页面源码
     */
    String getSource();
    
    /**
     * 阻塞方式执行脚本.
     * @param script 脚本
     */
    void syncExecute(String script);
    
    /**
     * 同步方式执行脚本.
     * @param script 脚本
     */
    void asyncExecute(String script);
    
    /**
     * 获取所有cookie.
     */
    void getAllCookies();
    
    /**
     * 根据名称获取指定cookie.
     * @param name cookie名称
     */
    void getCookie(String name);
    
    /**
     * 删除所有cookie.
     */
    void delAllCookies();
    
    /**
     * 根据名称删除指定cookie.
     * @param name cookie名称
     */
    void delCookie(String name);
    
    /**
     * 查找单个元素.
     * @return 元素
     */
    Element findElement();
    
    /**
     * 查找多个元素.
     * @return 元素列表
     */
    List<Element> findElements();
    
    /**
     * 获取超时时间.
     * @return 时间
     */
    long getTimeouts();
    
    /**
     * 设置超时时间.
     */
    void setTimeouts();
    
    /**
     * 关闭浏览器.
     */
    void close();
    
    /**
     * 获取会话.
     * @return 会话
     */
    PhoenixSession getSession();
}
