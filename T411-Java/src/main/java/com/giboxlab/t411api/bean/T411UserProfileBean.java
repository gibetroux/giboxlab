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
 * The Class T411UserProfileBean.
 */
public class T411UserProfileBean {

    /**
     * Username.
     */
    @JsonProperty("username")
    private String username;

    /**
     * Gender.
     */
    @JsonProperty("gender")
    private String gender;

    /**
     * Age.
     */
    @JsonProperty("age")
    private String age;

    /**
     * Avatar.
     */
    @JsonProperty("avatar")
    private String avatar;

    /**
     * Total downloaded.
     */
    @JsonProperty("downloaded")
    private String downloaded;

    /**
     * Total uploaded.
     */
    @JsonProperty("uploaded")
    private String uploaded;

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
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender
     *            the new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age
     *            the new age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets the avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar.
     *
     * @param avatar
     *            the new avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets the downloaded.
     *
     * @return the downloaded
     */
    public String getDownloaded() {
        return downloaded;
    }

    /**
     * Sets the downloaded.
     *
     * @param downloaded
     *            the new downloaded
     */
    public void setDownloaded(String downloaded) {
        this.downloaded = downloaded;
    }

    /**
     * Gets the uploaded.
     *
     * @return the uploaded
     */
    public String getUploaded() {
        return uploaded;
    }

    /**
     * Sets the uploaded.
     *
     * @param uploaded
     *            the new uploaded
     */
    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

}
