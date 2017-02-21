package com.example.newyorktimestopstories.view;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorktimestopstories.R;

/**
 * Created by raheelkhan on 2/20/17.
 */

public class StoryController extends ButterKnifeController {
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_section, container, false);
    }
}
