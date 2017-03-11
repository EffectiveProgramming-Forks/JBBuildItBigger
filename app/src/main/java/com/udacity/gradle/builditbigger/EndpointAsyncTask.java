package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jeff.breunig.backend.myApi.MyApi;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by jkbreunig on 3/9/17.
 */

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi mApi;
    private final String LOG_TAG = EndpointAsyncTask.class.getSimpleName();
    private EndPointCallback endpointCallback;

    @Override
    protected String doInBackground(Void... params) {

        if (mApi == null) {
            mApi = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://jbjokes-161105.appspot.com/_ah/api/").build();
        }

        try {
            return mApi.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground: ", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Timber.d("JOKE: " + result);
        if (endpointCallback != null) {
            endpointCallback.onComplete(result);
        }
    }

    /**
     * @param endpointCallback, implementation of {@link EndPointCallback}
     */
    public EndpointAsyncTask setCallbackListener(EndPointCallback endpointCallback) {
        this.endpointCallback = endpointCallback;
        return this;
    }

    public interface EndPointCallback {
        /**
         * Called when {@link EndpointAsyncTask} is completed
         *
         * @param result null in case of error else a joke string.
         */
        void onComplete(String result);
    }
}