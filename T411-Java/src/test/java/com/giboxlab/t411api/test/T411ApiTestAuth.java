/*
 * Copyright 2014 Jean-Baptiste ROUX
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */
package com.giboxlab.t411api.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.giboxlab.t411api.T411Api;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.giboxlab.t411api.test.enums.MockType;
import com.giboxlab.t411api.test.mock.MockT411Api;

/**
 * Testing auth process
 */
public class T411ApiTestAuth {

    /**
     * Test when an error occur on auth (wrong password)
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testFailLogin() throws T411RequestException, T411InitException, T411AuthException, IOException {

        new MockT411Api(MockType.AUTH_FAIL);

        try {
            new T411Api().auth("test", "test");
        } catch (T411AuthException e) {
            Assert.assertEquals(T411AuthException.PREFIX_MESSAGE + "107Wrong password", e.getMessage());
        }

    }

    /**
     * Test when the API return null (shouldn't..)
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testLoginReturnNull() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_RETURN_NULL);
        try {
            new T411Api().auth("test", "test");
        } catch (T411RequestException e) {
            Assert.assertEquals(T411RequestException.PREFIX_MESSAGE + "API return null", e.getMessage());
        }
    }

    /**
     * Test auth w/o login
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testLoginNoUser() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_ERROR);
        try {
            new T411Api().auth(null, "test");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Login and password must be specified and not null", e.getMessage());
        }
    }

    /**
     * Test auth w/o login
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testLoginNoUser2() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_ERROR);
        try {
            new T411Api().auth("", "test");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Login and password must be specified and not null", e.getMessage());
        }
    }

    /**
     * Test auth w/o pass
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testLoginNoPass() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_ERROR);
        try {
            new T411Api().auth("test", "");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Login and password must be specified and not null", e.getMessage());
        }
    }

    /**
     * Test auth w/o pass
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testLoginNoPass2() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_ERROR);
        try {
            new T411Api().auth("test", null);
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Login and password must be specified and not null", e.getMessage());
        }
    }

    /**
     * Test auth success.
     *
     * @throws T411RequestException
     *             Exception when something happen on request
     * @throws T411InitException
     *             Exception when something miss while initializing
     * @throws T411AuthException
     *             Exception when something happen on auth / token expired
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuthSuccess() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.AUTH_SUCCESS);
        T411Api t411api = new T411Api();
        t411api.auth("toto", "toto");
        Assert.assertEquals("123", t411api.getRequestClient().getAuthBean().getUid());
        Assert.assertEquals("123456789:123456789abcdefghijklmnopqrstuvwxyz", t411api.getRequestClient().getAuthBean().getToken());
    }

}
