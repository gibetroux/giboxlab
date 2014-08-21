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
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.giboxlab.t411api.T411Api;
import com.giboxlab.t411api.bean.T411SearchResultBean;
import com.giboxlab.t411api.bean.T411TorrentBean;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.giboxlab.t411api.test.enums.MockType;
import com.giboxlab.t411api.test.mock.MockT411Api;

/**
 * The Class T411ApiTestSearch.
 */
public class T411ApiTestSearch {

    /**
     * Test search success.
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchSuccess() throws T411RequestException, T411InitException, T411AuthException, IOException {

        new MockT411Api(MockType.SEARCH_SUCCESS);

        T411Api t411api = new T411Api();
        T411SearchResultBean results = t411api.search("her");

        Assert.assertEquals("2646", results.getTotal());
        Assert.assertEquals("her", results.getQuery());
        Assert.assertEquals("10", results.getLimit());
        Assert.assertEquals("0", results.getOffset());
        List<T411TorrentBean> list = results.getTorrents();
        Assert.assertEquals("2012-05-27 05:42:11", list.get(0).getAdded());
        Assert.assertEquals("623", list.get(0).getCategory());
        Assert.assertEquals("audio-music", list.get(0).getCategoryImage());
        Assert.assertEquals("Musique", list.get(0).getCategoryName());
        Assert.assertEquals("11", list.get(0).getComments());
        Assert.assertEquals("4624623", list.get(0).getId());
        Assert.assertEquals("1", list.get(0).getIsVerified());
        Assert.assertEquals("0", list.get(0).getLeechers());
        Assert.assertEquals("Patti Smith And Her Band-Live Aux Vieilles Charrues 2004.MP3 320Kbs", list.get(0).getName());
        Assert.assertEquals("4294433", list.get(0).getOwner());
        Assert.assertEquals("strong", list.get(0).getPrivacy());
        Assert.assertEquals("patti-smith-and-her-band-live-aux-vieilles-charrues-2004-mp3-320kbs", list.get(0).getRewriteName());
        Assert.assertEquals("2", list.get(0).getSeeders());
        Assert.assertEquals("174218592", list.get(0).getSize());
        Assert.assertEquals("229", list.get(0).getTimesCompleted());
        Assert.assertEquals("fredarras", list.get(0).getUsername());

    }

    /**
     * Test search without result
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchSuccessEmpty() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.SEARCH_SUCCESS_EMPTY);

        T411Api t411api = new T411Api();
        T411SearchResultBean results = t411api.search("vvvvvvvv");

        Assert.assertEquals("0", results.getTotal());
        Assert.assertEquals("vvvvvvvv", results.getQuery());
        Assert.assertEquals("10", results.getLimit());
        Assert.assertEquals("0", results.getOffset());
        List<T411TorrentBean> list = results.getTorrents();
        Assert.assertEquals(0, list.size());
    }

    /**
     * Test search w/o query
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchFail() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.SEARCH_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.search("");
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Search params should not be empty", e.getMessage());
        }
    }

    /**
     * Test search w/o query
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchFail2() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.SEARCH_FAIL);

        T411Api t411api = new T411Api();
        try {
            t411api.search(null);
        } catch (T411InitException e) {
            Assert.assertEquals(T411InitException.PREFIX_MESSAGE + "Search params should not be empty", e.getMessage());
        }
    }

    /**
     * Test search when returning new val.
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchError() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.SEARCH_ERROR);

        T411Api t411api = new T411Api();
        try {
            t411api.search("her");
        } catch (T411RequestException e) {
            Assert.assertTrue(e.getMessage().startsWith(T411RequestException.PREFIX_MESSAGE + "Unrecognized field \"newval\""));
        }
    }

    /**
     * Test search before auth.
     *
     * @throws T411RequestException
     *             the t411 request exception
     * @throws T411InitException
     *             the t411 init exception
     * @throws T411AuthException
     *             the t411 auth exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testSearchNoAuth() throws T411RequestException, T411InitException, T411AuthException, IOException {
        new MockT411Api(MockType.SEARCH_NO_AUTH);

        T411Api t411api = new T411Api();
        try {
            t411api.search("her");
        } catch (T411AuthException e) {
            Assert.assertEquals(T411AuthException.PREFIX_MESSAGE + "0Token empty. Please re-auth", e.getMessage());
        }
    }

}
