package com.example.newyorktimestopstories.presenter;

import com.example.newyorktimestopstories.StoriesService;
import com.example.newyorktimestopstories.model.Result;
import com.example.newyorktimestopstories.model.StoriesResult;
import com.example.newyorktimestopstories.model.Story;
import com.example.newyorktimestopstories.view.SectionView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by raheelkhan on 2/19/17.
 */

public class SectionPresenter {
    @Inject
    StoriesService storiesService;

    private DisposableObserver<StoriesResult> observer;

    private SectionView view;

    @Inject
    public SectionPresenter() {
    }

    public void bindView(SectionView view){
        this.view = view;
    }

    public void unBindView(){
        if (view!=null){
            this.view = null;
        }

        if (observer!=null){
            observer.dispose();
        }
    }

    public void getStories(String section){
        observer = storiesService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<StoriesResult>() {
                    @Override
                    public void onNext(StoriesResult storiesResult) {
                        List<Story> stories = new ArrayList<Story>();
                        if (storiesResult != null && storiesResult.getResults() != null) {
                            for (Result result : storiesResult.getResults()) {
                                Story story = new Story();
                                story.setTitle(result.getTitle());
                                story.setDescription(result.getAbstract());
                                story.setUrl(result.getUrl());
                                if (result.getMultimedia().size() > 0) {
                                    story.setThumbnail(result.getMultimedia().get(0).getUrl());
                                }
                                stories.add(story);
                            }
                        }
                        if (view!=null) {
                            view.setStories(stories);
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (view!=null){
                            view.showError();
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


}
