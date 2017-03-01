package com.example.newyorktimestopstories.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.newyorktimestopstories.R;
import com.example.newyorktimestopstories.Section;
import com.example.newyorktimestopstories.StoriesApplication;
import com.example.newyorktimestopstories.model.Story;
import com.example.newyorktimestopstories.presenter.SectionPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by raheelkhan on 2/20/17.
 */

public class SectionController extends ButterKnifeController implements SectionView{
    private static final String KEY_SECTION = "section";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.overlay_story)
    FrameLayout overlay;

    @Inject
    Context context;

    @Inject
    SectionPresenter presenter;

    private StoriesAdapter adapter;

    public SectionController(Bundle args) {
        super(args);
        StoriesApplication.getInstance().getComponent().inject(this);

        adapter = new StoriesAdapter(context);
        adapter.getOnClickObservable().subscribeWith(new DisposableObserver<Story>() {
            @Override
            public void onNext(Story story) {
                getChildRouter(overlay)
                        .setPopsLastView(true)
                        .setRoot(RouterTransaction.with(new StoryController(story))
                                .pushChangeHandler(new FadeChangeHandler())
                                .popChangeHandler(new FadeChangeHandler()));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public SectionController(Section section) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_SECTION, section.name())
                .build());
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        StoriesApplication.getInstance().getComponent().inject(this);

        presenter.bindView(this);
        presenter.getStories(getArgs().getString(KEY_SECTION));

        return super.onCreateView(inflater, container);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_section, container, false);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        presenter.unBindView();
    }

    @Override
    public void setStories(List<Story> stories) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setStories(stories);
        recyclerView.setAdapter(adapter);
    }
}
