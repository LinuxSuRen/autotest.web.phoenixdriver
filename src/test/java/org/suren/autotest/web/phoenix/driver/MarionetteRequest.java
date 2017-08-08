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

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * Marionette请求封装.
 * @author suren
 * @since 2017年8月8日 上午9:56:00
 */
public class MarionetteRequest
{
    private int index = 0;
    
    /**
     * @param cmd 命令
     * @param param 参数
     * @return 字符串格式命令
     */
    public String value(String cmd, JSONObject param)
    {
        JSONArray array = new JSONArray();
        array.add(0);
        array.add(++index);
        array.add(cmd);
        
        if(param == null || param.size() == 0)
        {
            array.add(JSONNull.getInstance().toString());
        }
        else
        {
            array.add(param.toString());
        }
        
        return array.toString();
    }
    
    /**
     * @param cmd 命令
     * @return 字符串格式命令
     */
    public String value(String cmd)
    {
        return value(cmd, null);
    }
    
    /**
     * @param cmd 命令
     * @param key 参数名
     * @param value 参数值
     * @return 字符串格式命令
     */
    public String value(String cmd, String key, String value)
    {
        JSONObject param = new JSONObject();
        param.put(key, value);
        
        return value(cmd, param);
    }
    
    /**
     * @param cmd 命令
     * @param param 参数
     * @return 字符串格式命令
     */
    public String valueWithMap(String cmd, Map<String, Object> param)
    {
        if(param == null)
        {
            return value(cmd);
        }
        
        JSONObject jsonParam = new JSONObject();
        param.forEach((key, value) -> {
            jsonParam.put(key, value == null ? JSONNull.getInstance() : value);
        });
        
        return value(cmd, jsonParam);
    }
}
