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
    
    String refresh();
    
    String goBack();
    
    String goForward();
    
    String findElement();
    
    String getElementAttribute();
}
