package com.example.newyorktimestopstories;

import com.example.newyorktimestopstories.model.StoriesResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by raheelkhan on 2/19/17.
 */

public interface StoriesService {
    @GET("/svc/topstories/v2/{section}.json")
    public Observable<StoriesResult> getTopStories(@Path("section") String section);
}
