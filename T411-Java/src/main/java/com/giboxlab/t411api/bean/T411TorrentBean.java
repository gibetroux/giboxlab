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
 * The Class T411TorrentBean.
 */
public class T411TorrentBean {

    /**
     * Torrent ID
     */
    @JsonProperty("id")
    private String id;

    /**
     * Torrent name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Rewrite name for URL rewriting.
     */
    @JsonProperty("rewritename")
    private String rewriteName;

    /**
     * Category ID
     */
    @JsonProperty("category")
    private String category;

    /**
     * Seeder count.
     */
    @JsonProperty("seeders")
    private String seeders;

    /**
     * Leecher count.
     */
    @JsonProperty("leechers")
    private String leechers;

    /**
     * Comments count.
     */
    @JsonProperty("comments")
    private String comments;

    /**
     * Torrent verified or not.
     */
    @JsonProperty("isVerified")
    private String isVerified;

    /**
     * Added date.
     */
    @JsonProperty("added")
    private String added;

    /**
     * Torrent full size
     */
    @JsonProperty("size")
    private String size;

    /**
     * Times completed.
     */
    @JsonProperty("times_completed")
    private String timesCompleted;

    /**
     * Owner ID.
     */
    @JsonProperty("owner")
    private String owner;

    /**
     * Category name.
     */
    @JsonProperty("categoryname")
    private String categoryName;

    /**
     * Category image.
     */
    @JsonProperty("categoryimage")
    private String categoryImage;

    /**
     * Owner username.
     */
    @JsonProperty("username")
    private String username;

    /**
     * Privacy.
     */
    @JsonProperty("privacy")
    private String privacy;

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
     * Gets the seeders.
     *
     * @return the seeders
     */
    public String getSeeders() {
        return seeders;
    }

    /**
     * Sets the seeders.
     *
     * @param seeders
     *            the new seeders
     */
    public void setSeeders(String seeders) {
        this.seeders = seeders;
    }

    /**
     * Gets the leechers.
     *
     * @return the leechers
     */
    public String getLeechers() {
        return leechers;
    }

    /**
     * Sets the leechers.
     *
     * @param leechers
     *            the new leechers
     */
    public void setLeechers(String leechers) {
        this.leechers = leechers;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments
     *            the new comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the checks if is verified.
     *
     * @return the checks if is verified
     */
    public String getIsVerified() {
        return isVerified;
    }

    /**
     * Sets the checks if is verified.
     *
     * @param isVerified
     *            the new checks if is verified
     */
    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Gets the added.
     *
     * @return the added
     */
    public String getAdded() {
        return added;
    }

    /**
     * Sets the added.
     *
     * @param added
     *            the new added
     */
    public void setAdded(String added) {
        this.added = added;
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size
     *            the new size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the times completed.
     *
     * @return the times completed
     */
    public String getTimesCompleted() {
        return timesCompleted;
    }

    /**
     * Sets the times completed.
     *
     * @param timesCompleted
     *            the new times completed
     */
    public void setTimesCompleted(String timesCompleted) {
        this.timesCompleted = timesCompleted;
    }

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner.
     *
     * @param owner
     *            the new owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
     * Gets the category image.
     *
     * @return the category image
     */
    public String getCategoryImage() {
        return categoryImage;
    }

    /**
     * Sets the category image.
     *
     * @param categoryImage
     *            the new category image
     */
    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username
     *            the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the privacy.
     *
     * @return the privacy
     */
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Sets the privacy.
     *
     * @param privacy
     *            the new privacy
     */
    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

}
