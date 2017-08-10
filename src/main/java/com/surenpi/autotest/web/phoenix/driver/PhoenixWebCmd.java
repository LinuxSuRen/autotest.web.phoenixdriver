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
 * @since 2017年8月9日 上午8:48:04
 */
public class PhoenixWebCmd implements DriverCmd
{

    @Override
    public String newSession()
    {
        return "/session";
    }

    @Override
    public String navi()
    {
        return "/session/{sessionId}/url";
    }

    @Override
    public String getCurrentUrl()
    {
        return "/session/{sessionId}/url";
    }

    @Override
    public String getTitle()
    {
        return "/session/{sessionId}/title";
    }

    @Override
    public String refresh()
    {
        return "/session/{sessionId}/refresh";
    }

    @Override
    public String goBack()
    {
        return "/session/{sessionId}/back";
    }

    @Override
    public String goForward()
    {
        return "/session/{sessionId}/forward";
    }

    @Override
    public String findElement()
    {
        return "/session/{sessionId}/element";
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
        return "/session/{sessionId}/element/{elementId}/attribute/{name}";
    }

}
