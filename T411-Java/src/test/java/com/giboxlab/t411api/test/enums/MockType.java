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
package com.giboxlab.t411api.test.enums;

/**
 * Kind of test
 */
public enum MockType {

    /** The auth fail. */
    AUTH_FAIL,
    /** The auth return null. */
    AUTH_RETURN_NULL,
    /** The auth error. */
    AUTH_ERROR,
    /** The auth success. */
    AUTH_SUCCESS,

    /** The search success. */
    SEARCH_SUCCESS,
    /** The search success empty. */
    SEARCH_SUCCESS_EMPTY,
    /** The search fail. */
    SEARCH_FAIL,
    /** The search error. */
    SEARCH_ERROR,
    /** The search no auth. */
    SEARCH_NO_AUTH,

    /** The detail fail. */
    DETAIL_FAIL,
    /** The detail success. */
    DETAIL_SUCCESS,
    /** The detail error. */
    DETAIL_ERROR,
    /** The detail no auth. */
    DETAIL_NO_AUTH,

    /** The profile success. */
    PROFILE_SUCCESS,
    /** The profile fail. */
    PROFILE_FAIL,
    /** The profile no auth. */
    PROFILE_NO_AUTH,
    /** The profile error. */
    PROFILE_ERROR;
}
