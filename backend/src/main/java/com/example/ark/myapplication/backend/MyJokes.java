package com.example.ark.myapplication.backend;

/**
 * Created by ark on 1/20/2016.
 */
/** The object model for the data we are sending through endpoints */
public class MyJokes {

    private String myJoke;

    public String getJoke() {
        return myJoke;
    }

    public void setJoke(String data) {
        myJoke = data;
    }
}
