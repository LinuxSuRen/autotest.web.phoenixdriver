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
 * @author suren
 * @since 2017年8月8日 上午10:47:04
 */
public class MarionetteCmd implements DriverCmd
{

    @Override
    public String newSession()
    {
        return "newSession";
    }

    @Override
    public String navi()
    {
        return "get";
    }

    @Override
    public String getCurrentUrl()
    {
        return "getCurrentUrl";
    }

    @Override
    public String getTitle()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String refresh()
    {
        return "refresh";
    }

    @Override
    public String goBack()
    {
        return "goBack";
    }

    @Override
    public String goForward()
    {
        return "goForward";
    }

    @Override
    public String findElement()
    {
        return "findElement";
    }

    @Override
    public String findElements()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getElementAttribute()
    {
        return "getElementAttribute";
    }

}
