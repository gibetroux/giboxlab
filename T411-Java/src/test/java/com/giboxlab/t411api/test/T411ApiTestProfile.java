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
import com.giboxlab.t411api.bean.T411UserProfileBean;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.giboxlab.t411api.test.enums.MockType;
import com.giboxlab.t411api.test.mock.MockT411Api;

/**
 * Test profile method
 */
public class T411ApiTestProfile {

    /**
     * Test profile success.
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
    public void testProfileSuccess() throws T411RequestException, T411InitException, T411AuthException, IOException {

        new MockT411Api(MockType.PROFILE_SUCCESS);

        T411Api t411api = new T411Api();
        T411UserProfileBean result = t411api.profile("447571");

        Assert.assertEquals("Rainstorm", result.getUsername());
        Assert.assertEquals("Male", result.getGender());
        Assert.assertEquals("999", result.getAge());
        Assert.assertEquals("23508433352", result.getDownloaded());
        Assert.assertEquals("3332137727054", result.getUploaded());

    }

    /**
     * Test profile with wrong ID
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
    public void testProfileNotFound() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.PROFILE_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.profile("1");
        } catch (T411RequestException e) {
            Assert.assertEquals(T411RequestException.PREFIX_MESSAGE + "User not found", e.getMessage());
        }
    }

    /**
     * Test profile process w/o profile ID
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
    public void testProfileFail() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.PROFILE_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.profile("");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "User id should not be empty", e.getMessage());
        }
    }

    /**
     * Test profile process w/o profile ID
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
    public void testProfileFail2() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.PROFILE_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.profile(null);
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "User id should not be empty", e.getMessage());
        }
    }

    /**
     * Test profile with new val (causing JSon error)
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
    public void testSearchError() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.PROFILE_ERROR);

        T411Api t411api = new T411Api();
        try {
            t411api.profile("447571");
        } catch (T411RequestException e) {
            Assert.assertTrue(e.getMessage().startsWith(T411RequestException.PREFIX_MESSAGE + "Unrecognized field \"newval\""));
        }
    }

    /**
     * Test profile before auth.
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
    public void testProfileNoAuth() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.PROFILE_NO_AUTH);

        T411Api t411api = new T411Api();
        try {
            t411api.profile("447571");
        } catch (T411AuthException e) {
            Assert.assertEquals(T411AuthException.PREFIX_MESSAGE + "0Token empty. Please re-auth", e.getMessage());
        }
    }

}
