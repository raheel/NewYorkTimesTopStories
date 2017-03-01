package com.example.newyorktimestopstories.view;

import com.example.newyorktimestopstories.model.Story;

import java.util.List;

/**
 * Created by raheelkhan on 2/20/17.
 */

public interface SectionView {
    public void setStories(List<Story> stories);
    public void showError();
}
