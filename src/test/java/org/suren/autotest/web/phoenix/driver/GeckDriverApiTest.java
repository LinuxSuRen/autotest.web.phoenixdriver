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

import org.apache.http.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.suren.autotest.interfaces.framework.SimpleHttpClient;

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
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    
    @Before
    public void newSession() throws ParseException, IOException
    {
        SocketAddress addr = new InetSocketAddress("127.0.0.1", 2828);
        socket = new Socket();
        socket.connect(addr);
        
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }
    
    @Test
    public void test() throws ParseException, IOException
    {
        JSONObject param = new JSONObject();
        param.put("sessionId", JSONNull.getInstance());
        param.put("capabilities", JSONNull.getInstance());
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(1);
        array.add("newSession");
        array.add(param.toString());

        String arrayStr = array.toString();
        String payload = String.format("%d:%s", arrayStr.length(), arrayStr);
        
        out.write(payload.getBytes());
        
        readResponse(in);
    }
    
    private void readResponse(InputStream in) throws IOException
    {
        StringBuffer response = new StringBuffer();
        
        read(in, response);
        read(in, response);
        
        System.out.println(response);
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
    
    @Test
    public void open() throws IOException
    {
        JSONObject param = new JSONObject();
        param.put("url", "http://surenpi.com");
        
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(2);
        array.add("get");
        array.add(param.toString());
        
        out.write(array.toString().getBytes());
        
        readResponse(in);
    }
    
    @After
    public void close() throws ParseException, IOException
    {
        socket.close();
    }
}
