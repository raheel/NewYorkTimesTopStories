package com.example.newyorktimestopstories.injection;

import com.example.newyorktimestopstories.view.HomeController;
import com.example.newyorktimestopstories.view.MainActivity;
import com.example.newyorktimestopstories.view.SectionController;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by raheelkhan on 2/21/17.
 */

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class, StorageModule.class})
public interface StoriesComponent {
    void inject(MainActivity activity);
    void inject(HomeController controller);
    void inject(SectionController controller);
}
