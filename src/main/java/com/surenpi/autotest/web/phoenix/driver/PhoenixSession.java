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

/**
 * 和浏览器连接的会话.
 * @author suren
 * @since 2017年8月2日 下午12:48:16
 */
public class PhoenixSession
{
    private String id;
    
    /**
     * 会话唯一标识.
     * @param id 会话唯一标识
     */
    public PhoenixSession(String id)
    {
        this.id = id;
    }

    /**
     * 会话唯一标识.
     * @return the id
     */
    public String getId()
    {
        return id;
    }
}
