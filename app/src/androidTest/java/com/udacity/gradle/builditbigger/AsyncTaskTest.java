package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.jokeslib;

import java.util.concurrent.TimeUnit;

/**
 * Created by ark on 1/20/2016.
 */
public class AsyncTaskTest extends AndroidTestCase {
    public void testVerifyAsyncResponds(){
        Context context =null;
        String joke = null;
        try{
            FetchJokeTask FJT = new FetchJokeTask();
            FJT.execute(context);
            joke = FJT.get(30, TimeUnit.SECONDS);
        }catch (Exception e){
            fail("Timed out");
        }
        jokeslib js = new jokeslib();
        assertNotNull("test",joke);
        assertEquals("not match with library",js.getJoke(),joke);
    }
}
