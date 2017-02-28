package com.example.newyorktimestopstories.injection;

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
public class AndroidModule {
    @Provides
    @Singleton
    RxSharedPreferences providesSharedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return RxSharedPreferences.create(preferences);
    }
}
