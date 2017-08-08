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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * tcp连接的命令执行器.
 * @author suren
 * @since 2017年8月8日 上午10:56:02
 */
public class TcpExecutor implements AsyncCmdExecutor
{
    private Socket socket;
    protected OutputStream out;
    protected InputStream in;
    
    public TcpExecutor(String host) throws IOException
    {
        this(host, 2828);
    }
    
    public TcpExecutor(String host, int port) throws IOException
    {
        SocketAddress addr = new InetSocketAddress(host, port);
        socket = new Socket();
        socket.connect(addr);
        
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    @Override
    public String execute(String cmd)
    {
        try
        {
            out.write(cmd.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public String getResponse()
    {
        StringBuffer dataBuf = new StringBuffer();
        
        int len = -1;
        byte[] buf = new byte[1024];
        
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
