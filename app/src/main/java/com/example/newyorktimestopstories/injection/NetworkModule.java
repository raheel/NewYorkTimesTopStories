package com.example.newyorktimestopstories.injection;

import com.example.newyorktimestopstories.StoriesService;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by raheelkhan on 2/19/17.
 */

@Module
public class NetworkModule {
        public static final HttpUrl API_URL = HttpUrl.parse("https://api.nytimes.com");

        @Provides
        @Singleton
        StoriesService provideCountriesService(OkHttpClient client) {
                return new Retrofit.Builder()
                        .client(client)
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
                        .create(StoriesService.class);
        }

        @Provides
        @Singleton
        OkHttpClient provideHttpClient(){
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
                okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
                return okHttpClient;
        }

}

