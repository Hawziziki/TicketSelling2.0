package com.me.movieticket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.entity.DiscoverItem;

import java.util.List;

/**
 * Created by MANITO on 2017/4/7.
 */

public class DiscoverListAdapter extends ArrayAdapter<DiscoverItem> {
    private TextView review_user_name;
    private TextView review_movie_name;
    private TextView review_score;
    private TextView review;
    private int resourceId;

    public DiscoverListAdapter(Context context, int textViewResourceId, List<DiscoverItem> objects) {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DiscoverItem discoverItem=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        review_user_name=(TextView) view.findViewById(R.id.review_user_name);
        review_user_name.setText(discoverItem.getReviewUserName());
        review_movie_name=(TextView) view.findViewById(R.id.review_movie_name);
        review_movie_name.setText(discoverItem.getReviewMovieName());
        review_score=(TextView)view.findViewById(R.id.score);
        review_score.setText(discoverItem.getReviewScore());
        review=(TextView)view.findViewById(R.id.review);
        review.setText(discoverItem.getReview());
        return view;
    }
}
