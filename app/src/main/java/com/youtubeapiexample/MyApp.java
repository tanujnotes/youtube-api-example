package com.youtubeapiexample;

import android.app.Application;

/**
 * Created by tan on 27/03/17.
 **/

public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.base_url))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
