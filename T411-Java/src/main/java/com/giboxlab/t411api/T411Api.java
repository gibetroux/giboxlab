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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giboxlab.t411api.bean.T411AuthBean;
import com.giboxlab.t411api.bean.T411DetailBean;
import com.giboxlab.t411api.bean.T411SearchResultBean;
import com.giboxlab.t411api.bean.T411TorrentBean;
import com.giboxlab.t411api.bean.T411UserProfileBean;
import com.giboxlab.t411api.enums.TopType;
import com.giboxlab.t411api.exceptions.T411AuthException;
import com.giboxlab.t411api.exceptions.T411InitException;
import com.giboxlab.t411api.exceptions.T411RequestException;
import com.google.gson.JsonElement;

/**
 * Main class <br/>
 * Example of use : <br/><br/>
 *      <i><b>T411Api api = new T411Api();<br/>
 *      api.auth("user", "pass");<br/>
 *      api.search("avatar");</b></i><br/><br/>
 * or<br/><br/>
 * <i><b>new T411Api().auth("user", "pass").search("avatar");</b></i>
 * @author Jean-Baptiste ROUX
 */
public class T411Api {

    /** 
     * The class that request the API with Apache HTTPClient. 
     * @see T411RequestClient
     */
    private T411RequestClient requestClient;

    /**
     *  
     * Jackson mapper for dynamic parsing of JSON objects.
     *
     * @see ObjectMapper
     */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Checks if this instance is authenticated.
     *
     * @return true, if is auth
     */
    public boolean isAuth() {
        return this.getRequestClient().getAuthBean() != null;
    }

    /**
     * Authenticating method.
     *
     * @param login T411 Login <b>Required</b>
     * @param password T411 Password <b>Required</b>
     * @return The instance for fast use ( new T411Api().auth("user", "pass").search("avatar"); )
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public T411Api auth(String login, String password) throws T411RequestException, T411InitException, T411AuthException {
        if (login==null || login.equals("")
                || password==null || password.equals("")) {
            throw new T411InitException("Login and password must be specified and not null");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", login);
        map.put("password", password);

        JsonElement response = this.getRequestClient().postHttp("auth", map);
        if (response == null) {
            throw new T411RequestException("API return null");
        }

        try {
            T411AuthBean authBean = mapper.readValue(response.toString(), T411AuthBean.class);
            this.getRequestClient().setAuthBean(authBean);
        } catch (IOException e) {
            throw new T411RequestException(e.getMessage());
        }

        return this;
    }

    /**
     * Search method <br />
     * Need to be authenticated first <br />
     *
     * @param search The searched string <b>Required</b>
     * @return T411SearchResultBean : Bean containing search result 
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public T411SearchResultBean search(String search) throws T411RequestException, T411InitException, T411AuthException {
        if (!this.isAuth()) {
            throw new T411AuthException("0", "Token empty. Please re-auth");
        }
        if (search == null || search.equals("")) {
            throw new T411InitException("Search params should not be empty");
        }

        try {
            return mapper.readValue(this.getRequestClient().postHttp("torrents/search/".concat(search)).toString(), T411SearchResultBean.class);
        } catch (IOException e) {
            throw new T411RequestException(e.getMessage());
        }
    }

    /**
     * Detail method, use it to get details of any torrent<br />
     * Need to be authenticated first <br />
     *
     * @param torrentId The torrent ID <b>Required</b>
     * @return T411DetailBean : Bean containing details 
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public T411DetailBean detail(String torrentId) throws T411RequestException, T411InitException, T411AuthException {
        if (!this.isAuth()) {
            throw new T411AuthException("0", "Token empty. Please re-auth");
        }
        if (torrentId == null || torrentId.equals("")) {
            throw new T411InitException("Torrent id should not be empty");
        }

        try {
            return mapper.readValue(this.getRequestClient().postHttp("torrents/details/".concat(torrentId)).toString(), T411DetailBean.class);
        } catch (IOException e) {
            throw new T411RequestException(e.getMessage());
        }

    }

    /**
     * Profile method, use it to get details of any member<br />
     * Need to be authenticated first <br />
     *
     * @param userId The user ID <b>Required</b>
     * @return T411UserProfileBean : Bean containing member infos
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public T411UserProfileBean profile(String userId) throws T411RequestException, T411InitException, T411AuthException {
        if (!this.isAuth()) {
            throw new T411AuthException("0", "Token empty. Please re-auth");
        }
        if (userId == null || userId.equals("")) {
            throw new T411InitException("User id should not be empty");
        }

        try {
            return mapper.readValue(this.getRequestClient().postHttp("users/profile/".concat(userId)).toString(), T411UserProfileBean.class);
        } catch (IOException e) {
            throw new T411RequestException(e.getMessage());
        }

    }

    /**
     * Top method, use it to get any kind of "Top List"<br />
     * Need to be authenticated first <br />
     *
     * @param type Type of top list. see TopType
     * @return A list of torrent.
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    @SuppressWarnings("unchecked")
    public ArrayList<T411TorrentBean> top(TopType type) throws T411RequestException, T411InitException, T411AuthException {
        if (type == null) {
            throw new T411InitException("The type should not be empty");
        }
        
        if (!this.isAuth()) {
            throw new T411AuthException("0", "Token empty. Please re-auth");
        }

        ArrayList<T411TorrentBean> list = new ArrayList<>();
        
        String url = "torrents/top/".concat(type.getVal());

        try {
            list = mapper.readValue(this.getRequestClient().postHttp(url).toString(), ArrayList.class);
        } catch (IOException e) {
            throw new T411RequestException(e.getMessage());
        }
        return list;
    }

    /**
     * Download file method.<br />
     * Need to be authenticated first <br />
     *
     * @param torrentId The torrent ID
     * @param torrentName The torrent name (file name eg. Avatar.torrent)
     * @param path The path where the torrent will be saved
     * @return Full path
     * @throws T411RequestException Exception when something happen on request
     * @throws T411InitException Exception when something miss while initializing
     * @throws T411AuthException Exception when something happen on auth / token expired
     */
    public String downloadFile(String torrentId, String torrentName, String path) throws T411RequestException, T411InitException, T411AuthException {
        if (!this.isAuth()) {
            throw new T411AuthException("0", "Token empty. Please re-auth");
        }
        if (torrentId == null || torrentId.equals("")) {
            throw new T411RequestException("Torrent id should not be empty");
        }
        String torrentFile = this.getRequestClient().getTorrentFile("torrents/download/".concat(torrentId), torrentName, path);

        return torrentFile;
    }

    /**
     * Gets the request client.
     *
     * @return the request client
     */
    public T411RequestClient getRequestClient() {
        if (requestClient == null) {
            requestClient = new T411RequestClient();
        }
        return requestClient;
    }

}
