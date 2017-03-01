package com.example.newyorktimestopstories.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newyorktimestopstories.R;
import com.example.newyorktimestopstories.model.Story;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by raheelkhan on 2/20/17.
 */

public class StoryController extends ButterKnifeController {

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_URL = "url";

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.description)
    TextView description;

    public StoryController(Story story) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TITLE, story.getTitle())
                .putString(KEY_DESCRIPTION, story.getDescription())
                .putString(KEY_URL, story.getUrl())
                .build());
    }

    public StoryController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_story, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        title.setText(getArgs().getString(KEY_TITLE));
        description.setText(getArgs().getString(KEY_DESCRIPTION));
    }

    @OnClick(R.id.read_more)
    public void onReadMoreClick(){
        String url = getArgs().getString(KEY_URL);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
