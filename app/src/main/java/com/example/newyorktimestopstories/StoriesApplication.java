package com.example.newyorktimestopstories;

import android.app.Application;

import com.example.newyorktimestopstories.injection.AppModule;
import com.example.newyorktimestopstories.injection.DaggerStoriesComponent;
import com.example.newyorktimestopstories.injection.NetworkModule;
import com.example.newyorktimestopstories.injection.StorageModule;
import com.example.newyorktimestopstories.injection.StoriesComponent;

/**
 * Created by raheelkhan on 2/21/17.
 */

public class StoriesApplication extends Application {
    private static StoriesApplication instance;
    private StoriesComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        component = DaggerStoriesComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .storageModule(new StorageModule()).build();
    }

    public static StoriesApplication getInstance() {
        return instance;
    }

    public StoriesComponent getComponent() {
        return component;
    }
}
