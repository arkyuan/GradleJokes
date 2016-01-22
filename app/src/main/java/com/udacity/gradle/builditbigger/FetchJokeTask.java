package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ark.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import app.com.ark.android.displayjokeslib.DisplayJokeActivity;

/**
 * Created by ark on 1/20/2016.
 */
public class FetchJokeTask extends AsyncTask<Context, Void, String> {

    private MyApi myApiService = null;
    private Context context;
    @Override
    protected String doInBackground(Context... params) {
        if(myApiService==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = params[0];
        try{
            return myApiService.jokesLib().execute().getJoke();
        } catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if(context!=null) {
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.JOKE_KEY, joke);
            context.startActivity(intent);
        }
    }
}

