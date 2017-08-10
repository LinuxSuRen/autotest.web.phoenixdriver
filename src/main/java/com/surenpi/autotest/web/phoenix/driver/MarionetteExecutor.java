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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author suren
 * @since 2017年8月8日 下午1:26:20
 */
public class MarionetteExecutor extends TcpExecutor
{

    /**
     * @param host
     * @throws IOException
     */
    public MarionetteExecutor(String host) throws IOException
    {
        super(host);
    }

    @Override
    public String execute(String cmd, String data)
    {
        String payload = String.format("%d:%s",
                cmd.length(), cmd);
        
        return super.execute(payload, data);
    }
    
    /**
     * @param index 请求序号
     * @return 响应
     */
    public String getResponse(int index)
    {
        List<JsonElement> jsonEleList = getJsonResponse();
        if(jsonEleList == null)
        {
            return null;
        }
        
        for(JsonElement ele : jsonEleList)
        {
            if(!(ele instanceof JsonArray))
            {
                continue;
            }
            
            if(index == ((JsonArray)ele).get(1).getAsInt())
            {
                return ele.toString();
            }
        }
        
        return null;
    }
    
    private List<JsonElement> getJsonResponse()
    {
        String response = getResponse();
        
        List<JsonElement> jsonEleList = new ArrayList<JsonElement>();
        int jsonIndex = 0;
        
        while(true)
        {
            int headIndex = response.indexOf(":", jsonIndex);
            if(headIndex != -1)
            {
                String head = response.substring(jsonIndex, headIndex);
                int headLen = head.length() + 1;
                int dataLen = Integer.parseInt(head);
                
                String json = response.substring(jsonIndex + headLen, jsonIndex + headLen + dataLen);
                
                jsonIndex += headLen + dataLen;
                
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonObject = jsonParser.parse(json);
                
                jsonEleList.add(jsonObject);
            }
            else
            {
                break;
            }
        }
        
        return jsonEleList;
    }

    @Override
    public String getResponse()
    {
        StringBuffer dataBuf = new StringBuffer();
        
        int len = -1;
        byte[] buf = new byte[10240];
        
        try
        {
            len = in.read(buf, 0, 10);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        
        if(len <= 0)
        {
            return null;
        }
        String head = new String(buf, 0, 10);
        int index = head.indexOf(":");
        
        String dataLenStr = head.substring(0, index);
        
        int dataLen = Integer.parseInt(dataLenStr);
        try
        {
            len = in.read(buf, 10, dataLen - 10 + index + 1);
            dataBuf.append(new String(buf, 0, dataLen + index + 1));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return dataBuf.toString();
    }

}
