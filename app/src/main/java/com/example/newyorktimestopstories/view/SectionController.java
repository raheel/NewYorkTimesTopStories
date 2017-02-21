package com.example.newyorktimestopstories.view;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newyorktimestopstories.R;

import butterknife.BindView;

/**
 * Created by raheelkhan on 2/20/17.
 */

public class SectionController extends ButterKnifeController {

    @BindView(R.id.text)
    TextView textView;

    int num;

    public SectionController() {
    }

    public SectionController(int num) {
        this.num = num;
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_section, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        textView.setText("Section " + num);

    }
}
