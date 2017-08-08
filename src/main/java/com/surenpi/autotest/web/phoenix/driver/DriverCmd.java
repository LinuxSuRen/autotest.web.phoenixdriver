package com.surenpi.autotest.web.phoenix.driver;

/**
 * 驱动命令.
 * @author suren
 * @since 2017年8月7日 下午6:23:37
 */
public interface DriverCmd
{
    /**
     * 新建会话.
     * @return 新建会话
     */
    String newSession();
    
    /**
     * 地址导航
     * @return 地址导航
     */
    String navi();
    
    /**
     * 获取当前地址.
     * @return 当前地址
     */
    String getCurrentUrl();
    
    /**
     * 获取title
     * @return title
     */
    String getTitle();
    
    /**
     * 刷新页面.
     * @return 刷新页面
     */
    String refresh();
    
    /**
     * 回退.
     * @return 回退
     */
    String goBack();
    
    /**
     * 前进.
     * @return 前进
     */
    String goForward();
    
    /**
     * 查找单个元素.
     * @return 查找单个元素
     */
    String findElement();
    
    /**
     * 查找多个元素.
     * @return 查找多个元素
     */
    String findElements();
    
    /**
     * 获取元素属性.
     * @return 获取元素属性
     */
    String getElementAttribute();
}
