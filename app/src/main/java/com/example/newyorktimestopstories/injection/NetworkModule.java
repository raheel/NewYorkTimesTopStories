package com.example.newyorktimestopstories.injection;

import com.example.newyorktimestopstories.BuildConfig;
import com.example.newyorktimestopstories.StoriesService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(StoriesService.class);
        }

        @Provides
        @Singleton
        OkHttpClient provideHttpClient(){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.readTimeout(60, TimeUnit.SECONDS);
                builder.connectTimeout(60, TimeUnit.SECONDS);
                builder.addInterceptor(new Interceptor(){

                        @Override
                        public Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();

                                HttpUrl originalHttpUrl = originalRequest.url();

                                HttpUrl url = originalHttpUrl.newBuilder()
                                        .addQueryParameter("api-key", BuildConfig.NYTIMES_API_KEY)
                                        .build();

                                Request.Builder requestBuilder = originalRequest.newBuilder()
                                        .url(url);

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                        }
                });
                builder.addInterceptor(logging);
                return builder.build();
        }

}

