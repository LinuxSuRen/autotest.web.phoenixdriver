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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.surenpi.autotest.webui.ui.Element;

import net.sf.json.JSONObject;

/**
 * @author suren
 * @since 2017年8月8日 下午5:39:19
 */
public class PhoenixWebDriver implements PhoenixDriver
{
    private DriverCmd driverCmd = new PhoenixWebCmd();
    private CmdExecutor<JSONObject> executor;
    private String sessionId;
    
    public PhoenixWebDriver()
    {
        executor = new RestfulExecutor("localhost", 9515);
    }

    @Override
    public PhoenixSession newSession()
    {
        JSONObject jsonObj = new JSONObject();
        
        JSONObject chromeOptionsJson = new JSONObject();
        chromeOptionsJson.put("args", new String[]{});
        chromeOptionsJson.put("extensions", new String[]{});
        
        JSONObject desiredCapabilitiesJson = new JSONObject();
        desiredCapabilitiesJson.put("browserName", "chrome");
        desiredCapabilitiesJson.put("chromeOptions", chromeOptionsJson);
        desiredCapabilitiesJson.put("version", "");
        desiredCapabilitiesJson.put("platform", "ANY");
        jsonObj.put("desiredCapabilities", desiredCapabilitiesJson);
        
        jsonObj.put("cmd_method", "post");
        String response = executor.execute(driverCmd.newSession(), jsonObj);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
        
        sessionId = jsonObject.get("sessionId").getAsString();
        
        return new PhoenixSession(sessionId);
    }

    @Override
    public PhoenixDriver open(String url)
    {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("cmd_method", "post");
        jsonObj.put("url", url);
        jsonObj.put("{sessionId}", sessionId);
        
        executor.execute(driverCmd.navi(), null);
        
        return this;
    }

    @Override
    public PhoenixDriver refresh()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PhoenixDriver forward()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PhoenixDriver back()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTitle()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUrl()
    {
        // TODO Auto-generated method stub
        return null;
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
