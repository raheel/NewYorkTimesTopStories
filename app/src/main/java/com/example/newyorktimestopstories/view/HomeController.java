package com.example.newyorktimestopstories.view;

/**
 * Created by raheelkhan on 2/20/17.
 */

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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeController extends ButterKnifeController implements HomeView {

    @BindView(R.id.section_tabs)
    TabLayout tabLayout;
    @BindView(R.id.stories_pager)
    ViewPager viewPager;

    private final RouterPagerAdapter pagerAdapter;


    public HomeController() {
        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public void configureRouter(Router router, int position) {
                if (!router.hasRootController()) {
                    Controller page = new SectionController(position);
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page " + position;
            }
        };
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
