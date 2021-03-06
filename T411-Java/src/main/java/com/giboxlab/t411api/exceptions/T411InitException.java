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
 * Exception throwed when an error occured during request init process
 */
public class T411InitException extends GenericT411Exception {

    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = -1805720693017108016L;

    /**
     * The prefix message.
     */
    public static final String PREFIX_MESSAGE = "Init error : ";

    /**
     * Instantiates a new t411 init exception.
     *
     * @param message
     *            the message
     */
    public T411InitException(String message) {
        super(PREFIX_MESSAGE + message);
    }
}
