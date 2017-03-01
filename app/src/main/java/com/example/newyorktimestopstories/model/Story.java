package com.example.newyorktimestopstories.model;

/**
 * Created by raheelkhan on 2/26/17.
 */

public class Story {
    private String title;
    private String thumbnail;
    private String description;
    private String url;

    private static final String NYT_THUMBNAIL = "http://i.imgur.com/b6pzOjN.png";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail!=null ? thumbnail : NYT_THUMBNAIL;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Story{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
