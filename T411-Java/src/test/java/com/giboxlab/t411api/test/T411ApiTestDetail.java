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
import com.giboxlab.t411api.bean.T411DetailBean;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.giboxlab.t411api.test.enums.MockType;
import com.giboxlab.t411api.test.mock.MockT411Api;

/**
 * Test detail process
 */
public class T411ApiTestDetail {

    /**
     * Test detail success.
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
    public void testDetailSuccess() throws T411RequestException, T411InitException, T411AuthException, IOException {

        new MockT411Api(MockType.DETAIL_SUCCESS);

        T411Api t411api = new T411Api();
        T411DetailBean result = t411api.detail("123");
        Assert.assertEquals("123", result.getId());
        Assert.assertEquals("Very scary movie", result.getName());
        Assert.assertEquals("very-scary-movie", result.getRewriteName());
        Assert.assertEquals("12", result.getCategory());
        Assert.assertEquals("Movie", result.getCategoryName());
    }

    /**
     * Test detail w/o ID
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
    public void testDetailNoId() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.DETAIL_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.detail(null);
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Torrent id should not be empty", e.getMessage());
        }
    }

    /**
     * Test detail w/o ID
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
    public void testDetailNoId2() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.DETAIL_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.detail("");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Torrent id should not be empty", e.getMessage());
        }
    }

    /**
     * Test detail on wrong torrent ID
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
    public void testDetailWrongId() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.DETAIL_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.detail("1");
        } catch (T411RequestException e) {
            Assert.assertEquals(T411RequestException.PREFIX_MESSAGE + "Torrent not found", e.getMessage());
        }
    }

    /**
     * Test detail with a new val, causing JSon error
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
    public void testDetailError() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.DETAIL_ERROR);

        T411Api t411api = new T411Api();
        try {
            t411api.detail("1");
        } catch (T411RequestException e) {
            Assert.assertTrue(e.getMessage().startsWith(T411RequestException.PREFIX_MESSAGE + "Unrecognized field \"newval\""));
        }
    }

    /**
     * Test detail with no auth.
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
    public void testDetailNoAuth() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.DETAIL_NO_AUTH);

        T411Api t411api = new T411Api();
        try {
            t411api.detail("1");
        } catch (T411AuthException e) {
            Assert.assertEquals(T411AuthException.PREFIX_MESSAGE + "0Token empty. Please re-auth", e.getMessage());
        }
    }

}
