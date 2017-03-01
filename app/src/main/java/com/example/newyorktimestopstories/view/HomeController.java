package com.example.newyorktimestopstories.view;

/**
 * Created by raheelkhan on 2/20/17.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.newyorktimestopstories.R;

import com.bluelinelabs.conductor.support.RouterPagerAdapter;
import com.example.newyorktimestopstories.Section;
import com.example.newyorktimestopstories.StoriesApplication;
import com.example.newyorktimestopstories.presenter.HomePresenter;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.newyorktimestopstories.Section.home;
import static com.example.newyorktimestopstories.Section.national;
import static com.example.newyorktimestopstories.Section.opinion;
import static com.example.newyorktimestopstories.Section.politics;
import static com.example.newyorktimestopstories.Section.world;


public class HomeController extends ButterKnifeController implements HomeView{

    @BindView(R.id.section_tabs)
    TabLayout tabLayout;
    @BindView(R.id.stories_pager)
    ViewPager viewPager;

    private RouterPagerAdapter pagerAdapter;

    @Inject
    HomePresenter presenter;

    public HomeController() {

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        StoriesApplication.getInstance().getComponent().inject(this);

        presenter.bindView(this);
        return super.onCreateView(inflater, container);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
    }

    @Override
    public void setSections(final Section[] sections) {
        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public int getCount() {
                return sections.length;
            }

            @Override
            public void configureRouter(Router router, int position) {
                if (!router.hasRootController()) {
                    Controller page = new SectionController(sections[position]);
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return sections[position].getDisplayName();
            }
        };

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
