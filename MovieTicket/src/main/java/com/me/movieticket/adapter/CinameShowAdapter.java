package com.me.movieticket.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.entity.CinemaInfo;
import com.me.movieticket.entity.UserItems;

import java.util.List;

/**
 * Created by MANITO on 2017/4/2.
 */

public class CinameShowAdapter extends ArrayAdapter<CinemaInfo> {

    private TextView cinema_name;
    private TextView cinema_grade;
    private TextView cinema_add;
    private int resourceId;

    public CinameShowAdapter(Context context, int textViewResourceId, List<CinemaInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CinemaInfo cinemainfo=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        cinema_name=(TextView) view.findViewById(R.id.cinema_name);
        cinema_name.setText(cinemainfo.getCinema_name());
        cinema_grade=(TextView) view.findViewById(R.id.cinema_grade);
        cinema_grade.setText(cinemainfo.getCinema_grade());
        cinema_add=(TextView)view.findViewById(R.id.cinema_add);
        cinema_add.setText(cinemainfo.getCinema_add());

        return view;
    }
}
