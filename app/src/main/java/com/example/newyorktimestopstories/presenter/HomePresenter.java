package com.example.newyorktimestopstories.presenter;

import android.content.SharedPreferences;

import com.example.newyorktimestopstories.Section;
import com.example.newyorktimestopstories.view.HomeView;
import com.example.newyorktimestopstories.view.SectionView;
import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.newyorktimestopstories.Section.home;
import static com.example.newyorktimestopstories.Section.national;
import static com.example.newyorktimestopstories.Section.opinion;
import static com.example.newyorktimestopstories.Section.politics;
import static com.example.newyorktimestopstories.Section.world;

/**
 * Created by raheelkhan on 2/26/17.
 */

public class HomePresenter {

    private static final String SECTION_KEY = "section";
    private static final Section[] DEFAULT_SECTIONS = new Section[]{
            home,
            opinion,
            world,
            national,
            politics
    };

    private HomeView view;

    private DisposableObserver<String> observer;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    RxSharedPreferences rxSharedPreferences;

    @Inject
    public HomePresenter() {
    }

    public void bindView(HomeView view){
        this.view = view;

        setupTabs();
    }

    public void unBindView(){
        if (view!=null){
            this.view = null;
        }

        if (observer!=null){
            observer.dispose();
        }
    }

    private void setupTabs() {
        Preference<String> sections = rxSharedPreferences.getString(SECTION_KEY, "");

        observer = sections.asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (view!=null && !"".equals(s)) {
                            view.setSections(Section.toSections(s));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        if (sections.get()==null || sections.get().equals("")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SECTION_KEY, Section.toCSV(DEFAULT_SECTIONS));
            editor.commit();
        }
    }
}

