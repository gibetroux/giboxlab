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
package com.giboxlab.t411api.test.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import mockit.Mock;
import mockit.MockUp;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.BasicHttpEntity;

import com.giboxlab.t411api.T411RequestClient;
import com.giboxlab.t411api.bean.T411AuthBean;
import com.giboxlab.t411api.test.enums.MockType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The Class MockT411RequestClient.
 */
public class MockT411RequestClient extends MockUp<T411RequestClient> {

    /**
     * The parse ret.
     */
    JsonObject parseRet = new JsonObject();

    /**
     * The do req ret.
     */
    HttpEntity doReqRet = new BasicHttpEntity();

    /**
     * The auth bean.
     */
    T411AuthBean authBean = new T411AuthBean();

    /**
     * Instantiates a new mock t411 request client.
     *
     * @param mockType
     *            the mock type
     */
    public MockT411RequestClient(MockType mockType) {
        JsonParser parser = new JsonParser();

        switch (mockType) {
        case AUTH_RETURN_NULL:
            parseRet = null;
            doReqRet = null;
            break;
        case SEARCH_FAIL:
            parseRet = null;
            break;
        case DETAIL_NO_AUTH:
        case SEARCH_NO_AUTH:
        case PROFILE_NO_AUTH:
            authBean = null;
            break;
        default:
            parseRet = parser.parse(getText(mockType.name())).getAsJsonObject();
            break;
        }

    }

    /**
     * Mock the parse method for returning dedicated String
     *
     * @param entity
     *            the entity
     * @return the json element
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Mock
    public JsonElement parse(HttpEntity entity) throws IOException {
        return parseRet;
    }

    /**
     * Gets the auth bean.
     *
     * @return the auth bean
     */
    @Mock
    public T411AuthBean getAuthBean() {
        return authBean;
    }

    /**
     * Sets the auth bean.
     *
     * @param authBean
     *            the new auth bean
     */
    @Mock
    public void setAuthBean(T411AuthBean authBean) {
        this.authBean = authBean;
    }

    /**
     * Do req.
     *
     * @param req
     *            the req
     * @param mapParam
     *            the map param
     * @return the http entity
     * @throws NoSuchAlgorithmException
     *             the no such algorithm exception
     * @throws KeyStoreException
     *             the key store exception
     * @throws KeyManagementException
     *             the key management exception
     * @throws UnsupportedEncodingException
     *             the unsupported encoding exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ClientProtocolException
     *             the client protocol exception
     */
    @Mock
    private HttpEntity doReq(String req, HashMap<String, String> mapParam)
            throws NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, UnsupportedEncodingException, IOException,
            ClientProtocolException {
        return doReqRet;
    }

    /**
     * Gets the text.
     *
     * @param file
     *            the file
     * @return the text
     */
    private String getText(String file) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(file + ".result");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            reader.close();
            return out.toString();
        } catch (IOException e) {
            return "{}";
        }
    }
}
