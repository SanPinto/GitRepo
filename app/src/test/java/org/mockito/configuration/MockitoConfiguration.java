/*
 * Copyright © 2016, Dubber
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

/**
 * Created by Mahesh Nayak on 02-08-2016.
 */
package org.mockito.configuration;

public class MockitoConfiguration extends DefaultMockitoConfiguration {

    @Override
    public boolean enableClassCache() {
        return false;
    }
}
