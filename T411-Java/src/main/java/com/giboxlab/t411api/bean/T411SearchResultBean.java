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
package com.giboxlab.t411api.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class T411SearchResultBean.
 */
public class T411SearchResultBean {

    /**
     * Search query
     */
    @JsonProperty("query")
    private String query;

    /**
     * Total result
     */
    @JsonProperty("total")
    private String total;

    /**
     * Offset
     */
    @JsonProperty("offset")
    private String offset;

    /**
     * Limit.
     */
    @JsonProperty("limit")
    private String limit;

    /**
     * Torrents list.
     */
    @JsonProperty("torrents")
    private List<T411TorrentBean> torrents;

    /**
     * Gets the query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query.
     *
     * @param query
     *            the new query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total
     *            the new total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * Gets the offset.
     *
     * @return the offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * Sets the offset.
     *
     * @param offset
     *            the new offset
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * Gets the limit.
     *
     * @return the limit
     */
    public String getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit
     *            the new limit
     */
    public void setLimit(String limit) {
        this.limit = limit;
    }

    /**
     * Gets the torrents.
     *
     * @return the torrents
     */
    public List<T411TorrentBean> getTorrents() {
        return torrents;
    }

    /**
     * Sets the torrents.
     *
     * @param torrents
     *            the new torrents
     */
    public void setTorrents(List<T411TorrentBean> torrents) {
        this.torrents = torrents;
    }

}
