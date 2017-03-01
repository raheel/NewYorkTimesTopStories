package com.example.newyorktimestopstories.injection;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by raheelkhan on 2/26/17.
 */

@Module
public class StorageModule {
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    RxSharedPreferences providesRxSharedPreferences(Application application, SharedPreferences sharedPreferences){
        return RxSharedPreferences.create(sharedPreferences);
    }
}
