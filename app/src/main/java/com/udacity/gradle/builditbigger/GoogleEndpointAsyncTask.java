package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.free.MainActivityFragment;

import java.io.IOException;

/**
 * Created by hannaholukoye on 26/10/2018.
 */

public class GoogleEndpointAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
        private static MyApi myApiService = null;
        private Context context;
        private MainActivityFragment mainActivityFragment;


    @Override
    protected String doInBackground(MainActivityFragment... mainActivityFragments) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override                         public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return String.valueOf(myApiService.sayHi(""));
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
        protected void onPostExecute(String result) {
            mainActivityFragment.loadedJoke = result;
            mainActivityFragment.launchDisplayJokeActivity();
        }

}
