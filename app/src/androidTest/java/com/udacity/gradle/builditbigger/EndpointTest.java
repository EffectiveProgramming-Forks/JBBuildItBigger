package com.udacity.gradle.builditbigger;

import android.support.annotation.UiThread;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by jkbreunig on 3/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointTest {

    @Test(timeout = 10*1000)
    @UiThread
    public void testEndpointAsyncTask() throws Exception {
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        assertNotNull("Null response - backend",
                endpointAsyncTask.execute().get());
    }
}