package com.youtubeapiexample;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tan on 27/03/17.
 **/

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}