package com.maid.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jgIAmwQwNfdMEaWxLecESBipCVwLcEV2CnBnKGDn")
                // if defined
                .clientKey("zuQzdxKePQYJ1707eHgdjfRtGQJbTff9yi5iJQUy")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
