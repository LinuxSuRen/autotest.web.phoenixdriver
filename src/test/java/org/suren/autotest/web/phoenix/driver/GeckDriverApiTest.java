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

package org.suren.autotest.web.phoenix.driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.suren.autotest.interfaces.framework.SimpleHttpClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * @author suren
 * @date 2017年8月1日 下午3:29:55
 */
//@Ignore
public class GeckDriverApiTest
{
    private static Socket socket;
    private static OutputStream out;
    private static InputStream in;
    
    private static int num = 1;
    
    @BeforeClass
    public static void newSession() throws ParseException, IOException
    {
        SocketAddress addr = new InetSocketAddress("127.0.0.1", 2828);
        socket = new Socket();
        socket.connect(addr);
        
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }
    
    @Test
    public void test() throws ParseException, IOException, InterruptedException
    {
//        readResponse(in);
        
        JSONObject param = new JSONObject();
        param.put("sessionId", JSONNull.getInstance());
        param.put("capabilities", JSONNull.getInstance());
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("newSession");
        array.add(param.toString());
        
        out.write(toMsg(array.toString()));
        
        readResponse(in);
        
        open("http://baidu.com");
        Thread.sleep(1000);
        open("http://surenpi.com");
        Thread.sleep(2000);
        
        getCurrentUrl();
    }
    
    private byte[] toMsg(String cmd)
    {
        String payload = String.format("%d:%s", cmd.length(), cmd);
        System.out.println("request: " + payload);
        return payload.getBytes();
    }
    
    private String readResponse(InputStream in) throws IOException
    {
        StringBuffer response = new StringBuffer();
        
        read(in, response);
        read(in, response);
        
        System.out.println(response);
        
        return response.toString();
    }
    
    private boolean read(InputStream in, StringBuffer dataBuf) throws IOException
    {
        int len = -1;
        byte[] buf = new byte[1024];
        
        len = in.read(buf, 0, 10);
        if(len <= 0)
        {
            return false;
        }
        String head = new String(buf, 0, 10);
        int index = head.indexOf(":");
        
        String dataLenStr = head.substring(0, index);
        
        int dataLen = Integer.parseInt(dataLenStr);
        len = in.read(buf, 10, dataLen - 10 + index + 1);
        dataBuf.append(new String(buf, 0, dataLen + index + 1));
        
        return true;
    }
    
    public void open(String url) throws IOException
    {
        JSONObject param = new JSONObject();
        param.put("url", url);
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("get");
        array.add(param.toString());
        
        out.write(toMsg(array.toString()));
        
//        readResponse(in);
        
//        array = new JSONArray();
//        array.add(1);
//        array.add(num - 1);
//        array.add(JSONNull.getInstance());
//        array.add(new JSONArray());
//        out.write(toMsg(array.toString()));
    }
    
    public void getCurrentUrl() throws IOException
    {
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("getCurrentUrl");
        array.add(JSONNull.getInstance());
        
        out.write(toMsg(array.toString()));
        
        readResponse(in);
        
        refresh();
    }
    
    public void refresh() throws IOException
    {
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("refresh");
        array.add(JSONNull.getInstance());
        
        out.write(toMsg(array.toString()));
        
        //readResponse(in);
        
        goBack();
    }
    
    public void goBack() throws IOException
    {
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("goBack");
        array.add(JSONNull.getInstance());
        
        out.write(toMsg(array.toString()));
        
//        readResponse(in);
        
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        goForward();
    }
    
    public void goForward() throws IOException
    {
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("goForward");
        array.add(JSONNull.getInstance());
        
        out.write(toMsg(array.toString()));
        
        readResponse(in);
        
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        findElement();
    }
    
    public void findElement() throws IOException
    {
        JSONObject param = new JSONObject();
        param.put("using", "name");
        param.put("value", "archive-dropdown");
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("findElement");
        array.add(param.toString());
        
        out.write(toMsg(array.toString()));
        out.write(toMsg(array.toString()));
        
        final int nowNum = num - 1;
        String response = readResponse(in);
        List<JsonElement> eleList = jsonParse(response, nowNum);
        eleList.forEach((json) -> {
            if(((JsonArray)json).get(1).getAsInt() == nowNum)
            {
                String eleId = ((JsonArray)json).get(3).getAsJsonObject()
                        .get("value").getAsJsonObject()
                        .get("ELEMENT").getAsString();
                
                try
                {
                    findAttribute(eleId);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private List<JsonElement> jsonParse(String response, int num)
    {
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
                System.out.println("===" +  json);
                
                jsonIndex += headLen + dataLen;
                
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonObject = (JsonArray) jsonParser.parse(json);
                
                jsonEleList.add(jsonObject);
            }
            else
            {
                break;
            }
        }
        
        return jsonEleList;
    }
    
    public void findAttribute(String eleId) throws IOException
    {
        JSONObject param = new JSONObject();
        param.put("id", eleId);
        param.put("name", "name");
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(num++);
        array.add("getElementAttribute");
        array.add(param.toString());
        
        out.write(toMsg(array.toString()));
        out.write(toMsg(array.toString()));
        
        readResponse(in);
    }
    
    @AfterClass
    public static void close() throws ParseException, IOException
    {
        socket.close();
    }
}
