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
package com.giboxlab.t411api.exceptions;

/**
 * Generic exception herited by all API Exceptions
 */
public abstract class GenericT411Exception extends Exception {

    /**
     * UID for serialisationz
     */
    private static final long serialVersionUID = -2628260921343555780L;

    /**
     * Instantiates a new generic t411 exception.
     *
     * @param message
     *            the message
     */
    public GenericT411Exception(String message) {
        super(message);
    }
}
