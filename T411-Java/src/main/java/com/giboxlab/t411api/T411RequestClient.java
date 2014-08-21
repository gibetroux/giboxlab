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
package com.giboxlab.t411api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.giboxlab.t411api.bean.T411AuthBean;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * The class that request the API with Apache HTTPClient. 
 * Internal class
 */
public class T411RequestClient {

    /** 
     * T411 API Url 
     */
    private static final String BASE_URL = "https://api.t411.me/";

    /** 
     * Bean containing auth informations (uid/token) 
     */
    private T411AuthBean authBean = null;

    /**
     * Instantiates a new t411 request client.
     */
    public T411RequestClient() {
    }

    /**
     * @see T411RequestClient#postHttp(String, HashMap)
     *
     * @param req Request url (API_URL/%req%)
     * @return the json element
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public JsonElement postHttp(String req) throws T411RequestException, T411InitException, T411AuthException {
        return postHttp(req, null);
    }

    /**
     * Method used to contact the API
     *
     * @param req Request url (API_URL/%req%)
     * @param mapParam Hashmap containing parameters (for auth)
     * @return JSON from API
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public JsonElement postHttp(String req, HashMap<String, String> mapParam) throws T411RequestException, T411InitException, T411AuthException {
        try {
            HttpEntity entity = doReq(req, mapParam);

            if (entity == null) {
                return null;
            }

            JsonElement p1 = parse(entity);
            if (p1 != null && p1.isJsonObject()) {
                JsonObject resp = (JsonObject) p1;
                if (resp.get("error") != null) {
                    if ("auth".equalsIgnoreCase(req)) {
                        throw new T411AuthException(resp.get("code").getAsString(), resp.get("error").getAsString());
                    } else {
                        throw new T411RequestException(resp.get("error").getAsString());
                    }
                } else {
                    return resp;
                }
            } else if (p1 != null && p1.isJsonArray()) {
                JsonArray resp = (JsonArray) p1;
                return resp;
            }
        } catch (JsonSyntaxException | IllegalStateException | ParseException | IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new T411InitException(e.getMessage());
        }
        return null;
    }

    /**
     * Method used to download the torrent file
     *
     * @param req Request url (API_URL/%req%)
     * @param filename String containing the file name
     * @param path Path to local storage
     * @return The full path
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public String getTorrentFile(String req, String filename, String path) throws T411RequestException, T411InitException, T411AuthException {
        try {
            HttpEntity entity = doReq(req, null);

            if (entity != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(path.concat(filename));
                    entity.writeTo(fos);
                } catch (Exception e) {
                    throw new T411InitException(e.getMessage());
                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                }
                return path.concat(filename);
            }

        } catch (JsonSyntaxException | IllegalStateException | ParseException | IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new T411InitException(e.getMessage());
        }
        return null;
    }

    /**
     * Technical part of the request
     *
     * @param req the req
     * @param mapParam the map param
     * @return the http entity
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws KeyStoreException the key store exception
     * @throws KeyManagementException the key management exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ClientProtocolException the client protocol exception
     */
    private HttpEntity doReq(String req, HashMap<String, String> mapParam) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnsupportedEncodingException, IOException,
            ClientProtocolException {
        String url = BASE_URL + req;

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        HttpPost httppost;
        ArrayList<NameValuePair> postParameters;
        httppost = new HttpPost(url);

        postParameters = new ArrayList<NameValuePair>();
        if (mapParam != null) {
            Set<Entry<String, String>> entrySet = mapParam.entrySet();
            for (Entry<String, String> entry : entrySet) {
                postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        httppost.setEntity(new UrlEncodedFormEntity(postParameters));
        HttpRequestBase request = httppost;
        if (authBean != null) {
            request.setHeader("Authorization", authBean.getToken());
        }

        HttpResponse response = httpclient.execute(request);

        HttpEntity entity = response.getEntity();

        return entity;
    }

    /**
     * Parses the HTTP Entity to JSon element
     *
     * @param entity Entity to be parsed
     * @return JsonElement object
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public JsonElement parse(HttpEntity entity) throws IOException {
        JsonElement p1 = new JsonParser().parse(EntityUtils.toString(entity));
        return p1;
    }

    /**
     * Gets the authbean object
     *
     * @return the auth bean
     */
    public T411AuthBean getAuthBean() {
        return authBean;
    }

    /**
     * Sets the authbean object
     *
     * @param authBean the new auth bean
     */
    public void setAuthBean(T411AuthBean authBean) {
        this.authBean = authBean;
    }

}
