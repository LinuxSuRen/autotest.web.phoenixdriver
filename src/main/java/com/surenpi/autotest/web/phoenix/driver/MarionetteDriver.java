package com.surenpi.autotest.web.phoenix.driver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.suren.autotest.web.phoenix.driver.MarionetteRequest;

import com.surenpi.autotest.webui.ui.Element;

/**
 * @author suren
 * @since 2017年8月8日 上午11:14:13
 */
public class MarionetteDriver implements PhoenixDriver
{
    private MarionetteRequest request;
    private DriverCmd driverCmd = new MarionetteCmd();
    private AsyncCmdExecutor executor;
    
    public MarionetteDriver() throws IOException
    {
        executor = new MarionetteExecutor("localhost");
        request = new MarionetteRequest();
    }

    @Override
    public PhoenixSession newSession()
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sessionId", null);
        param.put("capabilities", null);
        
        String cmd = request.valueWithMap(driverCmd.newSession(),
                param);
        
        executor.execute(cmd);
        
        return null;
    }

    @Override
    public PhoenixDriver open(String url)
    {
        String cmd = request.value(driverCmd.navi(),
                "url", url);
        
        executor.execute(cmd);
        
        return this;
    }

    @Override
    public PhoenixDriver refresh()
    {
        String cmd = request.value(driverCmd.refresh());
        
        executor.execute(cmd);
        
        return this;
    }

    @Override
    public PhoenixDriver forward()
    {
        String cmd = request.value(driverCmd.goForward());
        
        executor.execute(cmd);
        
        return this;
    }

    @Override
    public PhoenixDriver back()
    {
        String cmd = request.value(driverCmd.goBack());
        
        executor.execute(cmd);
        
        return this;
    }

    @Override
    public String getTitle()
    {
        String cmd = request.value(driverCmd.getTitle());
        
        executor.execute(cmd);
        
        return executor.getResponse();
    }

    @Override
    public String getUrl()
    {
        String cmd = request.value(driverCmd.getCurrentUrl());
        
        executor.execute(cmd);
        
        return executor.getResponse();
    }

    @Override
    public String getSource()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void syncExecute(String script)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void asyncExecute(String script)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void getAllCookies()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void getCookie(String name)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void delAllCookies()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void delCookie(String name)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Element findElement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Element> findElements()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getTimeouts()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setTimeouts()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void close()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public PhoenixSession getSession()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
