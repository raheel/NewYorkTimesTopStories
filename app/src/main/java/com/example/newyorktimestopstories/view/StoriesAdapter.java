package com.example.newyorktimestopstories.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newyorktimestopstories.R;
import com.example.newyorktimestopstories.model.Story;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by raheelkhan on 2/25/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.CustomViewHolder> {
    private Context context;
    private List<Story> stories;
    private final PublishSubject<Story> onClickSubject = PublishSubject.create();

    public StoriesAdapter(Context context) {
        this.context = context;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
    @Override
    public  CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Story story = stories.get(position);
        holder.title.setText(story.getTitle());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubject.onNext(story);
            }
        });
        Picasso.with(context).load(story.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row)
        LinearLayout row;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public Observable<Story> getOnClickObservable() {
        return onClickSubject.hide();
    }
}
