package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jeff.breunig.backend.myApi.MyApi;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by jkbreunig on 3/9/17.
 */

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService;
    private final String LOG_TAG = EndpointAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(Void... params) {

        if (myApiService == null) {
            myApiService = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(BuildConfig.DEBUG);
                        }
                    })
                    .setRootUrl("https://android­app­backend.appspot.com/_ah/api/").build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground: ", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Timber.d("JOKE: " + result);
    }
}