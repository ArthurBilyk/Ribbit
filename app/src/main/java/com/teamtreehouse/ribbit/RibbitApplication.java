package com.teamtreehouse.ribbit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by notepc on 09.03.2015.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Edq4RS4UKZR6GgPtHssZDTWrtcbJxnc65eUL6XVP", "DMDBz6r2EdlIcOznvseTcNe2cRMT2bFPHWSW8oaY");

       /* ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }
}
