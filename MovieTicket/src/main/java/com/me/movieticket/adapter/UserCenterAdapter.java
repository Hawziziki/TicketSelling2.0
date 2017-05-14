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
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.me.movieticket.R;
import com.me.movieticket.entity.UserItems;
import com.me.movieticket.views.TabDiscover;

import java.util.List;

/**
 * Created by MANITO on 2017/3/27.
 */

public class UserCenterAdapter extends ArrayAdapter<UserItems>{

    private TextView useritem;
    private int resourceId;


    public UserCenterAdapter(Context context, int textViewResourceId, List<UserItems> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserItems userItems=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        useritem=(TextView) view.findViewById(R.id.userltem);
        useritem.setText(userItems.getItemname());
        return view;
    }
}
