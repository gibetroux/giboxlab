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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class T411DetailBean.
 */
public class T411DetailBean {

    /**
     * Torrent ID
     */
    @JsonProperty("id")
    private String id;

    /**
     * Torrent name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Name for rewrite URL.
     */
    @JsonProperty("rewritename")
    private String rewriteName;

    /**
     * The category ID.
     */
    @JsonProperty("category")
    private String category;

    /**
     * The category name.
     */
    @JsonProperty("categoryname")
    private String categoryName;

    /**
     * The torrent description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the rewrite name.
     *
     * @return the rewrite name
     */
    public String getRewriteName() {
        return rewriteName;
    }

    /**
     * Sets the rewrite name.
     *
     * @param rewriteName
     *            the new rewrite name
     */
    public void setRewriteName(String rewriteName) {
        this.rewriteName = rewriteName;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category
     *            the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the category name.
     *
     * @return the category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name.
     *
     * @param categoryName
     *            the new category name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
