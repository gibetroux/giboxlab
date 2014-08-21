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

import mockit.Mock;
import mockit.MockUp;

import com.giboxlab.t411api.T411Api;
import com.giboxlab.t411api.T411RequestClient;
import com.giboxlab.t411api.test.enums.MockType;

/**
 * Mocking T411Api
 */
public class MockT411Api extends MockUp<T411Api> {

    /**
     * The mock type.
     */
    private MockType mockType;

    /**
     * The mock RequestClient
     */
    T411RequestClient mockRC;

    /**
     * Instantiates a new mock t411 api.
     *
     * @param mockType
     *            the mock type
     */
    public MockT411Api(MockType mockType) {
        this.mockType = mockType;
    }

    /**
     * Gets the request client.
     *
     * @return the request client
     */
    @Mock
    public T411RequestClient getRequestClient() {
        if (mockRC == null) {
            mockRC = new MockT411RequestClient(mockType).getMockInstance();
        }
        return mockRC;
    }
}
