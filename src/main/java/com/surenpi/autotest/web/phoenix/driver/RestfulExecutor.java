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

import java.io.IOException;

import org.apache.http.ParseException;
import org.suren.autotest.interfaces.framework.SimpleHttpClient;

import net.sf.json.JSONObject;

/**
 * @author suren
 * @since 2017年8月9日 上午8:50:11
 */
public class RestfulExecutor implements CmdExecutor<JSONObject>
{
    private SimpleHttpClient client;
    
    public RestfulExecutor(String host, int port)
    {
        client = new SimpleHttpClient();
        client.setHost("http://" + host + ":" + port);
    }

    @Override
    public String execute(String cmd, JSONObject jsonObj)
    {
        StringBuffer cmdBuffer = new StringBuffer(cmd);
        String method = jsonObj.getString("cmd_method");
        jsonObj.keySet().forEach((key) -> {
            if(key.toString().startsWith("{"))
            {
                String cmdTmp = cmdBuffer.toString().replace(key.toString(), jsonObj.getString(key.toString()));
                cmdBuffer.delete(0, cmdBuffer.length());
                cmdBuffer.append(cmdTmp);
            }
        });
        switch(method)
        {
            case "post":
                return executePost(cmd, jsonObj);
            case "del":
                return executeDel(cmd, jsonObj);
            default:
                return executeGet(cmd, jsonObj);
                    
        }
    }
    
    /**
     * @param cmd 命令
     * @param jsonObj 参数
     * @return 结果
     */
    public String executePost(String cmd, JSONObject jsonObj)
    {
        try
        {
            return client.executeJsonPost(cmd, jsonObj);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * @param cmd 命令
     * @param jsonObj 参数
     * @return 结果
     */
    public String executeGet(String cmd, JSONObject jsonObj)
    {
        try
        {
            return client.executeGet(cmd);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * @param cmd 命令
     * @param jsonObj 参数
     * @return 结果
     */
    public String executeDel(String cmd, JSONObject jsonObj)
    {
        try
        {
            return client.executeDel(cmd);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}
